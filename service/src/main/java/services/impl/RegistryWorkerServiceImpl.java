package services.impl;

import dao.RegistryWorkerDAO;
import dao.impl.RegistryWorkerDAOImpl;
import entities.RegistryWorker;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import services.RegistryWorkerService;

public class RegistryWorkerServiceImpl extends AbstractServiceImpl implements RegistryWorkerService {
    private static volatile RegistryWorkerService INSTANCE = null;
    private RegistryWorkerDAO registryWorkerDAO = RegistryWorkerDAOImpl.getInstance();

    private RegistryWorkerServiceImpl(){

    }

    @Override
    public RegistryWorker save(RegistryWorker registryWorker) {
        try {
            registryWorkerDAO.save(registryWorker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registryWorker;
    }

    @Override
    public RegistryWorker get(Serializable id) {
        RegistryWorker registryWorker = new RegistryWorker();
        try {
            registryWorker = registryWorkerDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registryWorker;
    }

    @Override
    public void update(RegistryWorker registryWorker) {
        try {
            registryWorkerDAO.update(registryWorker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = registryWorkerDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public List<RegistryWorker> getAll() {
        List<RegistryWorker> list = new CopyOnWriteArrayList<>();
        try {
            list = registryWorkerDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static RegistryWorkerService getInstance(){
        RegistryWorkerService registryWorkerService = INSTANCE;
        if (registryWorkerService == null){
            synchronized (RegistryWorkerServiceImpl.class) {
                registryWorkerService = INSTANCE;
                if (registryWorkerService == null) {
                    INSTANCE = registryWorkerService = new RegistryWorkerServiceImpl();
                }
            }
        }

        return registryWorkerService;
    }
}
