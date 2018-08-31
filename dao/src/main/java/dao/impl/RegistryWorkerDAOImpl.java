package dao.impl;

import dao.DaoUtils;
import dao.NurseDAO;
import dao.RegistryWorkerDAO;
import db.ConnectionManager;
import entities.Nurse;
import entities.RegistryWorker;
import enums.Educations;
import enums.Sex;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RegistryWorkerDAOImpl implements RegistryWorkerDAO {
    private static volatile RegistryWorkerDAO INSTANCE = null;
    private static final String saveRegQuery = "INSERT INTO registry_workers (first_name, last_name, age, sex, education, experience, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String updateRegQuery = "UPDATE registry_workers SET first_name=?, last_name=?, age=?, sex=?, education=?, experience=?, user_id=? WHERE id=?";

    private static final String getRegQuery = "SELECT * FROM registry_workers WHERE id=?";

    private static final String deleteRegQuery = "DELETE FROM registry_workers WHERE id=?";

    private static final String getAllRegsQuery = "SELECT * FROM registry_workers";

    private PreparedStatement psRegSave;

    private PreparedStatement psRegUpdate;

    private PreparedStatement psRegGet;

    private PreparedStatement psRegDelete;

    private PreparedStatement psRegGetAll;

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psRegSave = connection.prepareStatement(saveRegQuery, Statement.RETURN_GENERATED_KEYS);

            psRegUpdate = connection.prepareStatement(updateRegQuery);

            psRegGet = connection.prepareStatement(getRegQuery);

            psRegDelete = connection.prepareStatement(deleteRegQuery);

            psRegGetAll = connection.prepareStatement(getAllRegsQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RegistryWorkerDAOImpl() {
    }

    public static RegistryWorkerDAO getInstance() {
        RegistryWorkerDAO regDAO = INSTANCE;
        if (regDAO == null) {
            synchronized (RegistryWorkerDAOImpl.class) {
                regDAO = INSTANCE;
                if (regDAO == null) {
                    INSTANCE = regDAO = new RegistryWorkerDAOImpl();
                }
            }
        }

        return regDAO;
    }


    @Override
    public RegistryWorker save(RegistryWorker registryWorker) throws SQLException {
        psRegSave.setString(1, registryWorker.getFirstName());
        psRegSave.setString(2, registryWorker.getLastName());
        psRegSave.setInt(3, registryWorker.getAge());
        psRegSave.setString(4,registryWorker.getSex().toString());
        psRegSave.setString(5, registryWorker.getEducation().toString());
        psRegSave.setInt(6, registryWorker.getExperience());
        psRegSave.setLong(7, registryWorker.getUserId());
        psRegSave.executeUpdate();
        ResultSet rs = psRegSave.getGeneratedKeys();
        if (rs.next()){
            registryWorker.setId(rs.getInt(1));
        }
        DaoUtils.close(rs);
        return registryWorker;
    }

    @Override
    public RegistryWorker get(Serializable id) throws SQLException {
        RegistryWorker registryWorker = new RegistryWorker();
        psRegGet.setInt(1, (int) id);
        psRegGet.executeQuery();
        ResultSet rs = psRegGet.getResultSet();
        if (rs.next()){
            registryWorker.setId(rs.getInt(1));
            registryWorker.setFirstName(rs.getString(2));
            registryWorker.setLastName(rs.getString(3));
            registryWorker.setAge(rs.getInt(4));
            registryWorker.setSex(Sex.valueOf(rs.getString(5)));
            registryWorker.setEducation(Educations.valueOf(rs.getString(6)));
            registryWorker.setExperience(rs.getInt(7));
            registryWorker.setUserId(rs.getLong(8));
        }
        DaoUtils.close(rs);
        return registryWorker;
    }

    @Override
    public void update(RegistryWorker registryWorker) throws SQLException {
        psRegUpdate.setInt(8, registryWorker.getId());
        psRegUpdate.setString(1,registryWorker.getFirstName());
        psRegUpdate.setString(2, registryWorker.getLastName());
        psRegUpdate.setInt(3, registryWorker.getAge());
        psRegUpdate.setString(4, registryWorker.getSex().toString());
        psRegUpdate.setString(5, registryWorker.getEducation().toString());
        psRegUpdate.setInt(6, registryWorker.getExperience());
        psRegUpdate.setLong(7, registryWorker.getUserId());
        psRegUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psRegDelete.setInt(1, (int) id);
        return psRegDelete.executeUpdate();
    }

    @Override
    public List<RegistryWorker> getAll() throws SQLException {
        psRegGetAll.executeQuery();
        ResultSet rs = psRegGetAll.getResultSet();
        List<RegistryWorker> list = new CopyOnWriteArrayList<>();
        while (rs.next()){
            RegistryWorker re = new RegistryWorker();
            re.setId(rs.getInt(1));
            re.setFirstName(rs.getString(2));
            re.setLastName(rs.getString(3));
            re.setAge(rs.getInt(4));
            re.setSex(Sex.valueOf(rs.getString(5)));
            re.setEducation(Educations.valueOf(rs.getString(6)));
            re.setExperience(rs.getInt(7));
            re.setUserId(rs.getLong(8));
            list.add(re);
        }
        DaoUtils.close(rs);
        return list;
    }
}
