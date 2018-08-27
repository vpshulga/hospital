package dao.impl;

import dao.DaoUtils;
import dao.DoctorDAO;
import db.ConnectionManager;
import entities.Doctor;
import enums.Educations;
import enums.Sex;
import enums.Specialties;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DoctorDAOImpl implements DoctorDAO{
    private static volatile DoctorDAO INSTANCE = null;
    private static final String saveDoctorQuery = "INSERT INTO doctors (first_name, last_name, age, sex, education, experience, spetiality, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String updateDoctorQuery = "UPDATE doctors SET first_name=?, last_name=?, age=?, sex=?, education=?, experience=?, spetiality=?, user_id=? WHERE id=?";

    private static final String getDoctorQuery = "SELECT * FROM doctors WHERE id=?";

    private static final String deleteDoctorQuery = "DELETE FROM doctors WHERE id=?";

    private static final String getAllDoctorsQuery = "SELECT * FROM doctors";

    private static final String getDoctorByUIDQuery = "SELECT * FROM doctors WHERE user_id=?";

    private PreparedStatement psDoctorSave;

    private PreparedStatement psDoctorUpdate;

    private PreparedStatement psDoctorGet;

    private PreparedStatement psDoctorDelete;

    private PreparedStatement psGetAllDoctors;

    private PreparedStatement psGetDoctorByUID;




    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psDoctorSave = connection.prepareStatement(saveDoctorQuery, Statement.RETURN_GENERATED_KEYS);

            psDoctorUpdate = connection.prepareStatement(updateDoctorQuery);

            psDoctorGet = connection.prepareStatement(getDoctorQuery);

            psDoctorDelete = connection.prepareStatement(deleteDoctorQuery);

            psGetAllDoctors = connection.prepareStatement(getAllDoctorsQuery);

            psGetDoctorByUID = connection.prepareStatement(getDoctorByUIDQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DoctorDAOImpl() {
    }

    public static DoctorDAO getInstance() {
        DoctorDAO doctorDAO = INSTANCE;
        if (doctorDAO == null) {
            synchronized (DoctorDAOImpl.class) {
                doctorDAO = INSTANCE;
                if (doctorDAO == null) {
                    INSTANCE = doctorDAO = new DoctorDAOImpl();
                }
            }
        }

        return doctorDAO;
    }


    @Override
    public Doctor save(Doctor doctor) throws SQLException {
        psDoctorSave.setString(1, doctor.getFirstName());
        psDoctorSave.setString(2, doctor.getLastName());
        psDoctorSave.setInt(3, doctor.getAge());
        psDoctorSave.setString(4, doctor.getSex().toString());
        psDoctorSave.setString(5, doctor.getEducation().toString());
        psDoctorSave.setInt(6, doctor.getExperience());
        psDoctorSave.setString(7, doctor.getSpetialty().toString());
        psDoctorSave.setInt(8, doctor.getUserId());
        psDoctorSave.executeUpdate();
        ResultSet rs = psDoctorSave.getGeneratedKeys();
        if (rs.next()){
            doctor.setId(rs.getInt(1));
        }
        DaoUtils.close(rs);
        return doctor;
    }

    @Override
    public Doctor get(Serializable id) throws SQLException {
        Doctor doctor = new Doctor();
        psDoctorGet.setInt(1, (int) id);
        psDoctorGet.executeQuery();
        ResultSet rs = psDoctorGet.getResultSet();
        if (rs.next()){
            doctor.setId(rs.getInt(1));
            doctor.setFirstName(rs.getString(2));
            doctor.setLastName(rs.getString(3));
            doctor.setAge(rs.getInt(4));
            doctor.setSex(Sex.valueOf(rs.getString(5)));
            doctor.setEducation(Educations.valueOf(rs.getString(6)));
            doctor.setExperience(rs.getInt(7));
            doctor.setSpetialty(Specialties.valueOf(rs.getString(8)));
            doctor.setUserId(rs.getInt(9));
        }
        DaoUtils.close(rs);
        return doctor;
    }

    @Override
    public void update(Doctor doctor) throws SQLException {
        psDoctorUpdate.setInt(9, doctor.getId());
        psDoctorUpdate.setString(1, doctor.getFirstName());
        psDoctorUpdate.setString(2, doctor.getLastName());
        psDoctorUpdate.setInt(3, doctor.getAge());
        psDoctorUpdate.setString(4, doctor.getSex().toString());
        psDoctorUpdate.setString(5, doctor.getEducation().toString());
        psDoctorUpdate.setInt(6, doctor.getExperience());
        psDoctorUpdate.setString(7, doctor.getSpetialty().toString());
        psDoctorUpdate.setInt(8, doctor.getUserId());
        psDoctorUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDoctorDelete.setInt(1, (int) id);
        return psDoctorDelete.executeUpdate();
    }

    @Override
    public List<Doctor> getAll() throws SQLException {
        psGetAllDoctors.executeQuery();
        ResultSet rs = psGetAllDoctors.getResultSet();
        List<Doctor> list = new CopyOnWriteArrayList<>();
        while (rs.next()){
            Doctor doc = new Doctor();
            doc.setId(rs.getInt(1));
            doc.setFirstName(rs.getString(2));
            doc.setLastName(rs.getString(3));
            doc.setAge(rs.getInt(4));
            doc.setSex(Sex.valueOf(rs.getString(5)));
            doc.setEducation(Educations.valueOf(rs.getString(6)));
            doc.setExperience(rs.getInt(7));
            doc.setSpetialty(Specialties.valueOf(rs.getString(8)));
            doc.setUserId(rs.getInt(9));
            list.add(doc);
        }
        DaoUtils.close(rs);
        return list;
    }

    @Override
    public Doctor getDoctorByUID(Serializable userID) throws SQLException {
        Doctor doctor = new Doctor();
        psGetDoctorByUID.setInt(1, (int) userID);
        psGetDoctorByUID.executeQuery();
        ResultSet rs = psGetDoctorByUID.getResultSet();
        if (rs.next()){
            doctor.setId(rs.getInt(1));
            doctor.setFirstName(rs.getString(2));
            doctor.setLastName(rs.getString(3));
            doctor.setAge(rs.getInt(4));
            doctor.setSex(Sex.valueOf(rs.getString(5)));
            doctor.setEducation(Educations.valueOf(rs.getString(6)));
            doctor.setExperience(rs.getInt(7));
            doctor.setSpetialty(Specialties.valueOf(rs.getString(8)));
            doctor.setUserId(rs.getInt(9));
        }
        DaoUtils.close(rs);
        return doctor;
    }
}
