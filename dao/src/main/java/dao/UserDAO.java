package dao;

import entities.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    User getByLogin(String login) throws SQLException;
    List<String> getAllLogins() throws SQLException;
}
