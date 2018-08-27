package dao;

import entities.Nurse;
import java.sql.SQLException;
import java.util.List;

public interface NurseDAO extends DAO<Nurse>{
    List<Nurse> getAll() throws SQLException;
}
