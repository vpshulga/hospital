package services.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entities.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import services.UserService;

public class UserServiceImpl extends AbstractServiceImpl implements UserService {
    private static volatile UserService INSTANCE = null;
    private UserDAO userDAO = UserDAOImpl.getInstance();

    private UserServiceImpl(){

    }

    @Override
    public User save(User user) {
        try {
            userDAO.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        User user = new User();
        try {
            user = userDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        try {
            userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        int countRows = 0;
        try {
            countRows = userDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    @Override
    public User getByLogin(String login) {
        User user = new User();
        try {
            user = userDAO.getByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<String> getAllLogins() {
        List<String> list = new CopyOnWriteArrayList<>();
        try {
            list = userDAO.getAllLogins();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static UserService getInstance(){
        UserService userService = INSTANCE;
        if (userService == null){
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }

        return userService;
    }
}
