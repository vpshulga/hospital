package dao.impl;


import dao.ComplaintDAO;
import dao.DaoUtils;
import dao.PatientDAO;
import db.ConnectionManager;
import entities.cards.Complaint;
import java.io.Serializable;
import java.sql.*;

public class ComplaintDAOImpl implements ComplaintDAO {
    private static volatile ComplaintDAO INSTANCE = null;
    private static final String saveComQuery = "INSERT INTO complaints (patient_id, text) VALUES (?, ?)";

    private static final String updateComQuery = "UPDATE complaints SET patient_id=?, text=? WHERE id=?";

    private static final String getComQuery = "SELECT * FROM complaints WHERE id=?";

    private static final String deleteComQuery = "DELETE FROM complaints WHERE id=?";

    private PreparedStatement psComSave;

    private PreparedStatement psComUpdate;

    private PreparedStatement psComGet;

    private PreparedStatement psComDelete;

    private PatientDAO psi = PatientDAOImpl.getInstance();

    {
        try {
            Connection connection = ConnectionManager.getConnection();
            psComSave = connection.prepareStatement(saveComQuery, Statement.RETURN_GENERATED_KEYS);

            psComUpdate = connection.prepareStatement(updateComQuery);

            psComGet = connection.prepareStatement(getComQuery);

            psComDelete = connection.prepareStatement(deleteComQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ComplaintDAOImpl() {
    }

    public static ComplaintDAO getInstance() {
        ComplaintDAO complaintDAO = INSTANCE;
        if (complaintDAO == null) {
            synchronized (ComplaintDAOImpl.class) {
                complaintDAO = INSTANCE;
                if (complaintDAO == null) {
                    INSTANCE = complaintDAO = new ComplaintDAOImpl();
                }
            }
        }

        return complaintDAO;
    }

    @Override
    public Complaint save(Complaint complaint) throws SQLException {
        psComSave.setInt(1, complaint.getPatient().getId());
        psComSave.setString(2, complaint.getText());
        psComSave.executeUpdate();
        ResultSet rs = psComSave.getGeneratedKeys();
        if (rs.next()){
            complaint.setId(1);
        }
        DaoUtils.close(rs);
        return complaint;
    }

    @Override
    public Complaint get(Serializable id) throws SQLException {
        Complaint complaint = new Complaint();
        psComGet.setInt(1, (int) id);
        psComGet.executeQuery();
        ResultSet rs = psComGet.getResultSet();
        if (rs.next()){
            complaint.setId(rs.getInt(1));
            complaint.setPatient(psi.get(rs.getInt(2)));
            complaint.setText(rs.getString(3));
        }
        return complaint;
    }

    @Override
    public void update(Complaint complaint) throws SQLException {
        psComUpdate.setInt(3, complaint.getId());
        psComUpdate.setInt(1, complaint.getPatient().getId());
        psComUpdate.setString(2, complaint.getText());
        psComUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psComDelete.setInt(1, (int) id);
        return psComDelete.executeUpdate();
    }
}
