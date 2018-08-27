package dao;

import entities.RegistryWorker;
import java.sql.SQLException;
import java.util.List;

public interface RegistryWorkerDAO extends DAO<RegistryWorker> {
    List<RegistryWorker> getAll() throws SQLException;
}
