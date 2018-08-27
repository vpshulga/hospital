package dao.impl;

import dao.DaoUtils;
import dao.DoctorDAO;
import dao.NurseDAO;
import db.ConnectionManager;
import entities.Nurse;
import enums.Educations;
import enums.Sex;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NurseDAOImpl implements NurseDAO {
    private static volatile NurseDAO INSTANCE = null;
    private static final String saveNurseQuery = "INSERT INTO nurses (first_name, last_name, age, sex, education, experience) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String updateNurseQuery = "UPDATE nurses SET first_name=?, last_name=?, age=?, sex=?, education=?, experience=? WHERE id=?";

    private static final String getNurseQuery = "SELECT * FROM nurses WHERE id=?";

    private static final String deleteNurseQuery = "DELETE FROM nurses WHERE id=?";

    private static final String getAllNursesQuery = "SELECT * FROM nurses";

    private PreparedStatement psNurseSave;

    private PreparedStatement psNurseUpdate;

    private PreparedStatement psNurseGet;

    private PreparedStatement psNurseDelete;

    private PreparedStatement psGetAllNurses;

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psNurseSave = connection.prepareStatement(saveNurseQuery, Statement.RETURN_GENERATED_KEYS);

            psNurseUpdate = connection.prepareStatement(updateNurseQuery);

            psNurseGet = connection.prepareStatement(getNurseQuery);

            psNurseDelete = connection.prepareStatement(deleteNurseQuery);

            psGetAllNurses = connection.prepareStatement(getAllNursesQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private NurseDAOImpl() {
    }

    public static NurseDAO getInstance() {
        NurseDAO nurseDAO = INSTANCE;
        if (nurseDAO == null) {
            synchronized (NurseDAOImpl.class) {
                nurseDAO = INSTANCE;
                if (nurseDAO == null) {
                    INSTANCE = nurseDAO = new NurseDAOImpl();
                }
            }
        }

        return nurseDAO;
    }

    @Override
    public Nurse save(Nurse nurse) throws SQLException {
        psNurseSave.setString(1, nurse.getFirstName());
        psNurseSave.setString(2, nurse.getLastName());
        psNurseSave.setInt(3, nurse.getAge());
        psNurseSave.setString(4,nurse.getSex().toString());
        psNurseSave.setString(5, nurse.getEducation().toString());
        psNurseSave.setInt(6, nurse.getExperience());
        psNurseSave.executeUpdate();
        ResultSet rs = psNurseSave.getGeneratedKeys();
        if (rs.next()){
            nurse.setId(rs.getInt(1));
        }
        DaoUtils.close(rs);
        return nurse;
    }

    @Override
    public Nurse get(Serializable id) throws SQLException {
        Nurse nurse = new Nurse();
        psNurseGet.setInt(1, (int) id);
        psNurseGet.executeQuery();
        ResultSet rs = psNurseGet.getResultSet();
        if (rs.next()){
            nurse.setId(rs.getInt(1));
            nurse.setFirstName(rs.getString(2));
            nurse.setLastName(rs.getString(3));
            nurse.setAge(rs.getInt(4));
            nurse.setSex(Sex.valueOf(rs.getString(5)));
            nurse.setEducation(Educations.valueOf(rs.getString(6)));
            nurse.setExperience(rs.getInt(7));
        }
        DaoUtils.close(rs);
        return nurse;
    }

    @Override
    public void update(Nurse nurse) throws SQLException {
        psNurseUpdate.setInt(7, nurse.getId());
        psNurseUpdate.setString(1,nurse.getFirstName());
        psNurseUpdate.setString(2, nurse.getLastName());
        psNurseUpdate.setInt(3, nurse.getAge());
        psNurseUpdate.setString(4, nurse.getSex().toString());
        psNurseUpdate.setString(5, nurse.getEducation().toString());
        psNurseUpdate.setInt(6, nurse.getExperience());
        psNurseUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psNurseDelete.setInt(1, (int) id);
        return psNurseDelete.executeUpdate();
    }

    @Override
    public List<Nurse> getAll() throws SQLException {
        psGetAllNurses.executeQuery();
        ResultSet rs = psGetAllNurses.getResultSet();
        List<Nurse> list = new CopyOnWriteArrayList<>();
        while (rs.next()){
            Nurse nurse = new Nurse();
            nurse.setId(rs.getInt(1));
            nurse.setFirstName(rs.getString(2));
            nurse.setLastName(rs.getString(3));
            nurse.setAge(rs.getInt(4));
            nurse.setSex(Sex.valueOf(rs.getString(5)));
            nurse.setEducation(Educations.valueOf(rs.getString(6)));
            nurse.setExperience(rs.getInt(7));
            list.add(nurse);
        }
        DaoUtils.close(rs);
        return list;
    }
}
