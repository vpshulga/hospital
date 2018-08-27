package dao.impl;

import dao.AppointmentDAO;
import dao.DaoUtils;
import dao.PatientDAO;
import db.ConnectionManager;
import entities.cards.Appointment;
import enums.AppointmentsType;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {
    private static volatile AppointmentDAO INSTANCE = null;
    private static final String saveAppQuery = "INSERT INTO appointments (patient_id, type ,text) VALUES (?, ?, ?)";

    private static final String updateAppQuery = "UPDATE appointments SET patient_id=?, type=? , text=? WHERE id=?";

    private static final String getAppQuery = "SELECT * FROM appointments WHERE id=?";

    private static final String deleteAppQuery = "DELETE FROM appointments WHERE id=?";

    private static final String getAppByPatientIdQuery = "SELECT * FROM appointments WHERE patient_id=?";

    private static final String deleteByPatID = "DELETE FROM appointments WHERE patient_id=?";

    private PreparedStatement psAppSave;

    private PreparedStatement psAppUpdate;

    private PreparedStatement psAppGet;

    private PreparedStatement psAppDelete;

    private PreparedStatement psAppGetByPatientId;

    private PreparedStatement psDeleteByPatId;

    private PatientDAO psi = PatientDAOImpl.getInstance();

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psAppSave = connection.prepareStatement(saveAppQuery, Statement.RETURN_GENERATED_KEYS);

            psAppUpdate = connection.prepareStatement(updateAppQuery);

            psAppGet = connection.prepareStatement(getAppQuery);

            psAppDelete = connection.prepareStatement(deleteAppQuery);

            psAppGetByPatientId = connection.prepareStatement(getAppByPatientIdQuery);

            psDeleteByPatId = connection.prepareStatement(deleteByPatID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AppointmentDAOImpl() {
    }

    public static AppointmentDAO getInstance() {
        AppointmentDAO appointmentDAO = INSTANCE;
        if (appointmentDAO == null) {
            synchronized (AppointmentDAOImpl.class) {
                appointmentDAO = INSTANCE;
                if (appointmentDAO == null) {
                    INSTANCE = appointmentDAO = new AppointmentDAOImpl();
                }
            }
        }

        return appointmentDAO;
    }

    @Override
    public Appointment save(Appointment appointment) throws SQLException {
        psAppSave.setInt(1, appointment.getPatient().getId());
        psAppSave.setString(2, appointment.getType().toString());
        psAppSave.setString(3, appointment.getText());
        psAppSave.executeUpdate();
        ResultSet rs = psAppSave.getGeneratedKeys();
        if (rs.next()){
            appointment.setId(rs.getInt(1));
        }
        DaoUtils.close(rs);
        return appointment;
    }

    @Override
    public Appointment get(Serializable id) throws SQLException {
        Appointment appointment = new Appointment();
        psAppGet.setInt(1, (int) id);
        psAppGet.executeQuery();
        ResultSet rs = psAppGet.getResultSet();
        if (rs.next()){
            appointment.setId(rs.getInt(1));
            appointment.setPatient(psi.get(rs.getInt(2)));
            appointment.setType(AppointmentsType.valueOf(rs.getString(3)));
            appointment.setText(rs.getString(4));
        }
        DaoUtils.close(rs);
        return appointment;
    }

    @Override
    public void update(Appointment appointment) throws SQLException {
        psAppUpdate.setInt(4, appointment.getId());
        psAppUpdate.setInt(1, appointment.getPatient().getId());
        psAppUpdate.setString(2, appointment.getType().toString());
        psAppUpdate.setString(3, appointment.getText());
        psAppUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psAppDelete.setInt(1, (int) id);
        return psAppDelete.executeUpdate();
    }

    @Override
    public List<Appointment> getAllByPatientId(Serializable patientId) throws SQLException {
        List<Appointment> appointments = new CopyOnWriteArrayList<>();
        psAppGetByPatientId.setInt(1, (int) patientId);
        psAppGetByPatientId.executeQuery();
        ResultSet rs = psAppGetByPatientId.getResultSet();
        while (rs.next()){
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt(1));
            appointment.setPatient(psi.get(rs.getInt(2)));
            appointment.setType(AppointmentsType.valueOf(rs.getString(3)));
            appointment.setText(rs.getString(4));
            appointments.add(appointment);
        }
        DaoUtils.close(rs);
        return appointments;
    }

    @Override
    public int deleteByPatId(Serializable id) throws SQLException {
        psDeleteByPatId.setInt(1, (int) id);
        return psDeleteByPatId.executeUpdate();
    }
}
