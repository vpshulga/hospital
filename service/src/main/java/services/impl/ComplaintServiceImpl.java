package services.impl;

import dao.ComplaintDAO;
import dao.impl.ComplaintDAOImpl;
import entities.cards.Complaint;
import java.io.Serializable;
import java.sql.SQLException;
import services.ComplaintService;

public class ComplaintServiceImpl extends AbstractServiceImpl implements ComplaintService {
    private static volatile ComplaintService INSTANCE = null;
    private ComplaintDAO complaintDAO = ComplaintDAOImpl.getInstance();
    private ComplaintServiceImpl(){

    }

    @Override
    public Complaint save(Complaint complaint) {
        try {
            complaintDAO.save(complaint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaint;
    }

    @Override
    public Complaint get(Serializable id) {
        Complaint complaint = new Complaint();
        try {
            complaint = complaintDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaint;
    }

    @Override
    public void update(Complaint complaint) {
        try {
            complaintDAO.update(complaint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = complaintDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    public static ComplaintService getInstance(){
        ComplaintService complaintService = INSTANCE;
        if (complaintService == null){
            synchronized (ComplaintServiceImpl.class) {
                complaintService = INSTANCE;
                if (complaintService == null) {
                    INSTANCE = complaintService = new ComplaintServiceImpl();
                }
            }
        }

        return complaintService;
    }
}
