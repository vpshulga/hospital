package dao;

import entities.cards.Diagnosys;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DiagnosysDAO extends DAO<Diagnosys>{
    List<Diagnosys> getAll() throws SQLException;
    Diagnosys getByPatientId(Serializable patientId) throws SQLException;
    List<String> getAllByText() throws SQLException;
    int deleteByPatId(Serializable id) throws SQLException;

}
