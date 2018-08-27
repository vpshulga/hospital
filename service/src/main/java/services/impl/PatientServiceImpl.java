package services.impl;

import dao.PatientDAO;
import dao.impl.PatientDAOImpl;
import entities.Patient;
import services.PatientService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PatientServiceImpl extends AbstractServiceImpl implements PatientService {
    private static volatile PatientService INSTANCE = null;
    private PatientDAO patientDAO = PatientDAOImpl.getInstance();

    private PatientServiceImpl(){

    }

    @Override
    public Patient save(Patient patient) {
        try {
            patient = patientDAO.save(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public Patient get(Serializable id) {
        Patient patient = new Patient();
        try {
            patient = patientDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public void update(Patient patient) {

        try {
            patientDAO.update(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countDeletedRows = 0;
        try {
            countDeletedRows = patientDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countDeletedRows;
    }

    @Override
    public List<Patient> getAllByDoctorId(Serializable doctorId) {
        List<Patient> patients = new CopyOnWriteArrayList<>();
        try {
            patients = patientDAO.getAllByDoctorId(doctorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getByUserId(Serializable userId) {
        Patient patient = new Patient();
        try {
            patient = patientDAO.getByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public static PatientService getInstance(){
        PatientService patientService = INSTANCE;
        if (patientService == null){
            synchronized (PatientServiceImpl.class) {
                patientService = INSTANCE;
                if (patientService == null) {
                    INSTANCE = patientService = new PatientServiceImpl();
                }
            }
        }

        return patientService;
    }
}
