package services.impl;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractServiceImpl {

    public void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    public void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void rollback(){
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
