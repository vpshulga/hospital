package services.impl;

import dao.NurseDAO;
import dao.impl.NurseDAOImpl;
import entities.Nurse;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import services.NurseService;

public class NurseSrviceImpl extends AbstractServiceImpl implements NurseService {
    private static volatile NurseService INSTANCE = null;
    private NurseDAO nurseDAO = NurseDAOImpl.getInstance();

    private NurseSrviceImpl(){

    }

    @Override
    public Nurse save(Nurse nurse) {
        try {
            nurseDAO.save(nurse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    @Override
    public Nurse get(Serializable id) {
        Nurse nurse = new Nurse();
        try {
            nurse = nurseDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    @Override
    public void update(Nurse nurse) {
        try {
            nurseDAO.update(nurse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = nurseDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public List<Nurse> getAll() {
        List<Nurse> list = new CopyOnWriteArrayList<>();
        try {
            list = nurseDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static NurseService getInstance(){
        NurseService nurseService = INSTANCE;
        if (nurseService == null){
            synchronized (NurseSrviceImpl.class) {
                nurseService = INSTANCE;
                if (nurseService == null) {
                    INSTANCE = nurseService = new NurseSrviceImpl();
                }
            }
        }

        return nurseService;
    }
}
