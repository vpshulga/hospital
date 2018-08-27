package dao;

import entities.cards.Appointment;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO extends DAO<Appointment> {
    List<Appointment> getAllByPatientId(Serializable patientId) throws SQLException;
    int deleteByPatId(Serializable id) throws SQLException;

}
