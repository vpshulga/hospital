package services.impl;

import dao.DoctorDAO;
import dao.impl.DoctorDAOImpl;
import entities.Doctor;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import services.DoctorService;

public class DoctorServiceImpl extends AbstractServiceImpl implements DoctorService{
    private static volatile DoctorService INSTANCE = null;
    private DoctorDAO doctorDAO = DoctorDAOImpl.getInstance();

    private DoctorServiceImpl(){

    }

    @Override
    public Doctor save(Doctor doctor) {
        try {
            doctorDAO.save(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public Doctor get(Serializable id) {
        Doctor doc = new Doctor();
        try {
            doc = doctorDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    public void update(Doctor doctor) {
        try {
            doctorDAO.update(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = doctorDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> list = new CopyOnWriteArrayList<>();
        try {
            list = doctorDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Doctor getDoctorByUID(Serializable userId) {
        Doctor doc = new Doctor();
        try {
            doc = doctorDAO.getDoctorByUID(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static DoctorService getInstance(){
        DoctorService doctorService = INSTANCE;
        if (doctorService == null){
            synchronized (DoctorServiceImpl.class) {
                doctorService = INSTANCE;
                if (doctorService == null) {
                    INSTANCE = doctorService = new DoctorServiceImpl();
                }
            }
        }

        return doctorService;
    }
}
