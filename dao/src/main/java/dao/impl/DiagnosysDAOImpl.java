package dao.impl;

import dao.DaoUtils;
import dao.DiagnosysDAO;
import dao.PatientDAO;
import db.ConnectionManager;
import entities.cards.Diagnosys;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DiagnosysDAOImpl implements DiagnosysDAO {
    private static volatile DiagnosysDAO INSTANCE = null;
    private static final String saveDiaQuery = "INSERT INTO diagnoses (patient_id, text) VALUES (?, ?)";

    private static final String updateDiaQuery = "UPDATE diagnoses SET patient_id=?, text=? WHERE id=?";

    private static final String getDiaQuery = "SELECT * FROM diagnoses WHERE id=?";

    private static final String deleteDiaQuery = "DELETE FROM diagnoses WHERE id=?";

    private static final String getAllDiaQuery = "SELECT * FROM diagnoses";

    private static final String getByPatientIdQuery = "SELECT * FROM diagnoses WHERE patient_id=?";

    private static final String getAllByTextQuery = "SELECT text FROM diagnoses";

    private static final String deleteByPatIdQuery = "DELETE FROM diagnoses WHERE patient_id=?";

    private PreparedStatement psDiaSave;

    private PreparedStatement psDiaUpdate;

    private PreparedStatement psDiaGet;

    private PreparedStatement psDiaDelete;

    private PreparedStatement psGetAllDia;

    private PreparedStatement psGetByPatientId;

    private PreparedStatement psGetAllByText;

    private PreparedStatement psDiaDeleteByPatId;

    private PatientDAO psi = PatientDAOImpl.getInstance();

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psDiaSave = connection.prepareStatement(saveDiaQuery, Statement.RETURN_GENERATED_KEYS);

            psDiaUpdate = connection.prepareStatement(updateDiaQuery);

            psDiaGet = connection.prepareStatement(getDiaQuery);

            psDiaDelete = connection.prepareStatement(deleteDiaQuery);

            psGetAllDia = connection.prepareStatement(getAllDiaQuery);

            psGetByPatientId = connection.prepareStatement(getByPatientIdQuery);

            psGetAllByText = connection.prepareStatement(getAllByTextQuery);

            psDiaDeleteByPatId = connection.prepareStatement(deleteByPatIdQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DiagnosysDAOImpl() {
    }

    public static DiagnosysDAO getInstance() {
        DiagnosysDAO diagnosysDAO = INSTANCE;
        if (diagnosysDAO == null) {
            synchronized (DiagnosysDAOImpl.class) {
                diagnosysDAO = INSTANCE;
                if (diagnosysDAO == null) {
                    INSTANCE = diagnosysDAO = new DiagnosysDAOImpl();
                }
            }
        }

        return diagnosysDAO;
    }

    @Override
    public Diagnosys save(Diagnosys diagnosys) throws SQLException {
        psDiaSave.setInt(1, diagnosys.getPatient().getId());
        psDiaSave.setString(2, diagnosys.getText());
        psDiaSave.executeUpdate();
        ResultSet rs = psDiaSave.getGeneratedKeys();
        if (rs.next()){
            diagnosys.setId(rs.getInt(1));
        }
        DaoUtils.close(rs);
        return diagnosys;
    }

    @Override
    public Diagnosys get(Serializable id) throws SQLException {
        Diagnosys diagnosys = new Diagnosys();
        psDiaGet.setInt(1, (int) id);
        psDiaGet.executeQuery();
        ResultSet rs = psDiaGet.getResultSet();
        if (rs.next()){
            diagnosys.setId(rs.getInt(1));

            diagnosys.setPatient(psi.get(rs.getInt(2)));
            diagnosys.setText(rs.getString(3));
        }
        DaoUtils.close(rs);
        return diagnosys;
    }

    @Override
    public void update(Diagnosys diagnosys) throws SQLException {
        psDiaUpdate.setInt(3, diagnosys.getId());
        psDiaUpdate.setInt(1, diagnosys.getPatient().getId());
        psDiaUpdate.setString(2, diagnosys.getText());
        psDiaUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDiaDelete.setInt(1, (int) id);
        return psDiaDelete.executeUpdate();
    }

    @Override
    public List<Diagnosys> getAll() throws SQLException {
        psGetAllDia.executeQuery();
        ResultSet rs = psGetAllDia.getResultSet();
        List<Diagnosys> list = new CopyOnWriteArrayList<>();
        while (rs.next()){
            Diagnosys diagnosys = new Diagnosys();
            diagnosys.setId(rs.getInt(1));
            diagnosys.setPatient(psi.get(rs.getInt(2)));
            diagnosys.setText(rs.getString(3));
            list.add(diagnosys);
        }
        DaoUtils.close(rs);
        return list;
    }

    @Override
    public Diagnosys getByPatientId(Serializable patientId) throws SQLException {
        Diagnosys diagnosys = new Diagnosys();
        psGetByPatientId.setInt(1, (int) patientId);
        psGetByPatientId.executeQuery();
        ResultSet rs = psGetByPatientId.getResultSet();
        if (rs.next()){
            diagnosys.setId(rs.getInt(1));
            diagnosys.setPatient(psi.get(rs.getInt(2)));
            diagnosys.setText(rs.getString(3));
        }
        DaoUtils.close(rs);
        return diagnosys;
    }

    @Override
    public List<String> getAllByText() throws SQLException {
        List<String> list = new CopyOnWriteArrayList<>();
        psGetAllByText.executeQuery();
        ResultSet rs = psGetAllByText.getResultSet();
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }

    @Override
    public int deleteByPatId(Serializable id) throws SQLException {
        psDiaDeleteByPatId.setInt(1, (int) id);
        return psDiaDeleteByPatId.executeUpdate();
    }
}
