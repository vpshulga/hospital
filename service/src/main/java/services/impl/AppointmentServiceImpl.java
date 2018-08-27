package services.impl;

import dao.AppointmentDAO;
import dao.impl.AppointmentDAOImpl;
import entities.cards.Appointment;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import services.AppointmentService;

public class AppointmentServiceImpl extends AbstractServiceImpl implements AppointmentService{
    private static volatile AppointmentService INSTANCE = null;

    private AppointmentDAO appointmentDAO = AppointmentDAOImpl.getInstance();

    private AppointmentServiceImpl(){

    }

    @Override
    public Appointment save(Appointment appointment) {
        try {
            appointmentDAO.save(appointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    @Override
    public Appointment get(Serializable id) {
        Appointment appointment = new Appointment();
        try {
            appointment = appointmentDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    @Override
    public void update(Appointment appointment) {
        try {
            appointmentDAO.update(appointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            startTransaction();
            countRows = appointmentDAO.delete(id);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public List<Appointment> getAllByPatientId(Serializable patientId) {
        List<Appointment> appointments = new CopyOnWriteArrayList<>();
        try {
            appointments = appointmentDAO.getAllByPatientId(patientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public int deleteByPatId(Serializable id) {
        int countRows = 0;
        try {
            startTransaction();
            countRows = appointmentDAO.deleteByPatId(id);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
        return countRows;
    }

    public static AppointmentService getInstance(){
        AppointmentService appointmentService = INSTANCE;
        if (appointmentService == null){
            synchronized (AppointmentServiceImpl.class) {
                appointmentService = INSTANCE;
                if (appointmentService == null) {
                    INSTANCE = appointmentService = new AppointmentServiceImpl();
                }
            }
        }

        return appointmentService;
    }
}
