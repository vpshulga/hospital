package dao.impl;

import dao.UserDAO;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl extends GenericDaoImpl<User> implements UserDAO{

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl(Class<User> clazz) {
        super(clazz);
    }
}
