package services.impl;

import dao.DiagnosysDAO;
import dao.impl.DiagnosysDAOImpl;
import entities.Nurse;
import entities.cards.Diagnosys;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import services.DiagnosysService;

public class DiagnosysServiceImpl extends AbstractServiceImpl implements DiagnosysService{
    private static volatile DiagnosysService INSTANCE = null;
    private DiagnosysDAO diagnosysDAO = DiagnosysDAOImpl.getInstance();

    private DiagnosysServiceImpl(){

    }

    @Override
    public Diagnosys save(Diagnosys diagnosys) {
        try {
            diagnosysDAO.save(diagnosys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosys;
    }

    @Override
    public Diagnosys get(Serializable id) {
        Diagnosys diagnosys = new Diagnosys();
        try {
            diagnosys = diagnosysDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosys;
    }

    @Override
    public void update(Diagnosys diagnosys) {
        try {
            diagnosysDAO.update(diagnosys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = diagnosysDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public List<Diagnosys> getAll() {
        List<Diagnosys> list = new CopyOnWriteArrayList<>();
        try {
            list = diagnosysDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Diagnosys getByPatientId(Serializable patientId) {
        Diagnosys diagnosys = new Diagnosys();
        try {
            diagnosys = diagnosysDAO.getByPatientId(patientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosys;
    }

    @Override
    public List<String> getAllByText() {
        List<String> list = new CopyOnWriteArrayList<>();
        try {
            list = diagnosysDAO.getAllByText();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteByPatId(Serializable id) {
        int countRows = 0;
        try {
            countRows = diagnosysDAO.deleteByPatId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    public static DiagnosysService getInstance(){
        DiagnosysService diagnosysService = INSTANCE;
        if (diagnosysService == null){
            synchronized (DiagnosysServiceImpl.class) {
                diagnosysService = INSTANCE;
                if (diagnosysService == null) {
                    INSTANCE = diagnosysService = new DiagnosysServiceImpl();
                }
            }
        }

        return diagnosysService;
    }
}
