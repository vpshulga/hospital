package dao.impl;

import dao.DaoUtils;
import dao.PatientDAO;
import db.ConnectionManager;
import entities.Patient;

import enums.Sex;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PatientDAOImpl implements PatientDAO {
    private static volatile PatientDAO INSTANCE = null;
    private static final String savePatientQuery = "INSERT INTO patients (first_name, last_name, age, sex, address, complaint, doctor_id, entrance_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String saveAddressQuery = "INSERT INTO addresses (city, street, house, apartament) VALUES (?, ?, ?, ?)";

    private static final String updatePatientQuery = "UPDATE patients SET first_name=?, last_name=?, age=?, sex=?, complaint=?, doctor_id=?, entrance_date=?, user_id=? WHERE id=?";
    private static final String updateAddressQuery = "UPDATE addresses SET city=?, street=?, house=?, apartament=? WHERE id=?";

    private static final String getPatientQuery = "SELECT * FROM patients WHERE id=?";
    private static final String getAddressQuery = "SELECT * FROM addresses WHERE id=?";


    private static final String deletePatientQuery = "DELETE FROM patients WHERE id=?";
    private static final String deleteAddressQuery = "DELETE FROM addresses WHERE id=?";

    private static final String getAllPatientsByDoctorIdQuery = "SELECT * FROM patients WHERE doctor_id=?";
    private static final String getAllAddrByDoctorIdQuery = "SELECT * FROM addresses WHERE id=?";

    private static final String getPatientByUserIdQuery = "SELECT * FROM patients WHERE user_id=?";
    private static final String getAddressByUserIdQuery = "SELECT * FROM addresses WHERE id=?";

    private PreparedStatement psAddressSave;
    private PreparedStatement psPatientSave;

    private PreparedStatement psPatientUpdate;
    private PreparedStatement psAddressUpdate;

    private PreparedStatement psAddressGet;
    private PreparedStatement psPatientGet;

    private PreparedStatement psAddressDelete;
    private PreparedStatement psPatientDelete;

    private PreparedStatement psGetAllPatientsByDoctorId;
    private PreparedStatement psGetAllAddrByDoctorId;

    private PreparedStatement psAddressGetByUserId;
    private PreparedStatement psPatientGetByUserId;

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psAddressSave = connection.prepareStatement(saveAddressQuery, Statement.RETURN_GENERATED_KEYS);
            psPatientSave = connection.prepareStatement(savePatientQuery, Statement.RETURN_GENERATED_KEYS);

            psPatientUpdate = connection.prepareStatement(updatePatientQuery);
            psAddressUpdate = connection.prepareStatement(updateAddressQuery, Statement.RETURN_GENERATED_KEYS);

            psPatientGet = connection.prepareStatement(getPatientQuery);
            psAddressGet = connection.prepareStatement(getAddressQuery);

            psPatientDelete = connection.prepareStatement(deletePatientQuery);
            psAddressDelete = connection.prepareStatement(deleteAddressQuery);

            psGetAllPatientsByDoctorId = connection.prepareStatement(getAllPatientsByDoctorIdQuery);
            psGetAllAddrByDoctorId = connection.prepareStatement(getAllAddrByDoctorIdQuery);

            psPatientGetByUserId = connection.prepareStatement(getPatientByUserIdQuery);
            psAddressGetByUserId = connection.prepareStatement(getAddressByUserIdQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PatientDAOImpl() {
    }

    public static PatientDAO getInstance() {
        PatientDAO patientDAO = INSTANCE;
        if (patientDAO == null) {
            synchronized (PatientDAOImpl.class) {
                patientDAO = INSTANCE;
                if (patientDAO == null) {
                    INSTANCE = patientDAO = new PatientDAOImpl();
                }
            }
        }

        return patientDAO;
    }


    @Override
    public Patient save(Patient patient) throws SQLException {
        psAddressSave.setString(1, patient.getCity());
        psAddressSave.setString(2, patient.getStreet());
        psAddressSave.setInt(3, patient.getHouse());
        psAddressSave.setInt(4, patient.getApartment());
        psAddressSave.executeUpdate();
        ResultSet rs = psAddressSave.getGeneratedKeys();
        psPatientSave.setString(1, patient.getFirstName());
        psPatientSave.setString(2, patient.getLastName());
        psPatientSave.setInt(3, patient.getAge());
        psPatientSave.setString(4, patient.getSex().toString());
        if (rs.next()) {
            psPatientSave.setInt(5, rs.getInt(1));
        }
        psPatientSave.setString(6, patient.getComplaint());
        psPatientSave.setInt(7, patient.getDoctorId());
        psPatientSave.setString(8, patient.getEntranceDate());
        psPatientSave.setInt(9, patient.getUserId());
        psPatientSave.executeUpdate();
        ResultSet rs1 = psPatientSave.getGeneratedKeys();
        if (rs1.next()) {
            patient.setId(rs1.getInt(1));
        }

        DaoUtils.close(rs);
        DaoUtils.close(rs1);
        return patient;
    }

    @Override
    public Patient get(Serializable id) throws SQLException {
        Patient patient = new Patient();
        psPatientGet.setInt(1, (int) id);
        psPatientGet.executeQuery();
        ResultSet rs = psPatientGet.getResultSet();
        if (rs.next()) {
            psAddressGet.setInt(1, rs.getInt(6));
            psAddressGet.executeQuery();
            ResultSet rs1 = psAddressGet.getResultSet();
            if (rs1.next()) {
                patient.setId(rs.getInt(1));
                patient.setFirstName(rs.getString(2));
                patient.setLastName(rs.getString(3));
                patient.setAge(rs.getInt(4));
                patient.setSex(Sex.valueOf(rs.getString(5)));
                patient.setCity(rs1.getString(2));
                patient.setStreet(rs1.getString(3));
                patient.setHouse(rs1.getInt(4));
                patient.setApartment(rs1.getInt(5));
                patient.setComplaint(rs.getString(7));
                patient.setDoctorId(rs.getInt(8));
                patient.setEntranceDate(rs.getString(9));
                patient.setUserId(rs.getInt(10));
            }
            DaoUtils.close(rs1);
        }
        DaoUtils.close(rs);

        return patient;
    }

    @Override
    public void update(Patient patient) throws SQLException {
        psPatientUpdate.setInt(9, patient.getId());
        psPatientUpdate.setString(1, patient.getFirstName());
        psPatientUpdate.setString(2, patient.getLastName());
        psPatientUpdate.setInt(3, patient.getAge());
        psPatientUpdate.setString(4, patient.getSex().toString());
        psPatientUpdate.setString(5, patient.getComplaint());
        psPatientUpdate.setInt(6, patient.getDoctorId());
        psPatientUpdate.setString(7, patient.getEntranceDate());
        psPatientUpdate.setInt(8, patient.getUserId());
        psPatientUpdate.executeUpdate();

        psPatientGet.setInt(1, patient.getId());
        ResultSet rs = psPatientGet.executeQuery();
        if (rs.next()) {
            psAddressUpdate.setInt(5, rs.getInt(6));
            psAddressUpdate.setString(1, patient.getCity());
            psAddressUpdate.setString(2, patient.getStreet());
            psAddressUpdate.setInt(3, patient.getHouse());
            psAddressUpdate.setInt(4, patient.getApartment());
            psAddressUpdate.executeUpdate();
        }
        DaoUtils.close(rs);
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        int addressRows = 0;
        int patientRows = 0;
        psPatientGet.setInt(1, (int) id);
        ResultSet rs = psPatientGet.executeQuery();
        if (rs.next()) {
            psPatientDelete.setInt(1, (int) id);
            patientRows = psPatientDelete.executeUpdate();
            psAddressDelete.setInt(1, rs.getInt(6));
            addressRows = psAddressDelete.executeUpdate();
        }
        return addressRows + patientRows;
    }


    @Override
    public List<Patient> getAllByDoctorId(Serializable doctorId) throws SQLException {
        List<Patient> patients = new CopyOnWriteArrayList<>();
        psGetAllPatientsByDoctorId.setInt(1, (Integer) doctorId);
        psGetAllPatientsByDoctorId.executeQuery();
        ResultSet rs = psGetAllPatientsByDoctorId.getResultSet();
        while (rs.next()) {
            Patient patient = new Patient();
            psGetAllAddrByDoctorId.setInt(1, rs.getInt(6));
            psGetAllAddrByDoctorId.executeQuery();
            ResultSet rs1 = psGetAllAddrByDoctorId.getResultSet();
            if (rs1.next()){
                patient.setId(rs.getInt(1));
                patient.setFirstName(rs.getString(2));
                patient.setLastName(rs.getString(3));
                patient.setAge(rs.getInt(4));
                patient.setSex(Sex.valueOf(rs.getString(5)));
                patient.setCity(rs1.getString(2));
                patient.setStreet(rs1.getString(3));
                patient.setHouse(rs1.getInt(4));
                patient.setApartment(rs1.getInt(5));
                patient.setComplaint(rs.getString(7));
                patient.setDoctorId(rs.getInt(8));
                patient.setEntranceDate(rs.getString(9));
                patient.setUserId(rs.getInt(10));
            }
            DaoUtils.close(rs1);
            patients.add(patient);
        }
        DaoUtils.close(rs);

        return patients;
    }

    @Override
    public Patient getByUserId(Serializable userId) throws SQLException {
        Patient patient = new Patient();
        psPatientGetByUserId.setInt(1, (int) userId);
        psPatientGetByUserId.executeQuery();
        ResultSet rs = psPatientGetByUserId.getResultSet();
        if (rs.next()) {
            psAddressGetByUserId.setInt(1, rs.getInt(6));
            psAddressGetByUserId.executeQuery();
            ResultSet rs1 = psAddressGetByUserId.getResultSet();
            if (rs1.next()) {
                patient.setId(rs.getInt(1));
                patient.setFirstName(rs.getString(2));
                patient.setLastName(rs.getString(3));
                patient.setAge(rs.getInt(4));
                patient.setSex(Sex.valueOf(rs.getString(5)));
                patient.setCity(rs1.getString(2));
                patient.setStreet(rs1.getString(3));
                patient.setHouse(rs1.getInt(4));
                patient.setApartment(rs1.getInt(5));
                patient.setComplaint(rs.getString(7));
                patient.setDoctorId(rs.getInt(8));
                patient.setEntranceDate(rs.getString(9));
                patient.setUserId(rs.getInt(10));
            }
            DaoUtils.close(rs1);
        }
        DaoUtils.close(rs);

        return patient;
    }
}
