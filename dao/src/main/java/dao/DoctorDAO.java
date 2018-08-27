package dao;

import entities.Doctor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DoctorDAO extends DAO<Doctor>{
    List<Doctor> getAll() throws SQLException;
    Doctor getDoctorByUID(Serializable userId) throws SQLException;
}
