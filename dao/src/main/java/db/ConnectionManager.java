package db;

import java.sql.Connection;

public class ConnectionManager {
    private static ThreadLocal<Connection> con = new ThreadLocal<>();

    public static Connection getConnection() throws DbManagerException {
        try {
            if (con.get() == null) {
                con.set(DataSource.getInstance().getConnection());
            }
            return con.get();
        } catch (Exception e) {
            throw new DbManagerException("Ошибка получения соединения " +  e.getMessage());
        }
    }

}
