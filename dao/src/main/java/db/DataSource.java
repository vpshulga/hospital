package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    private static DataSource dataSource;
    private ComboPooledDataSource comboPooledDataSource;

    public static final String URL;
    public static final String DRIVER;
    public static final String USER;
    public static final String PASSWORD;
    public static final String HBM2DDL_AUTO;
    public static final String CURRENT_SESSION_CONTEXT_CLASS;
    private static ResourceBundle rb = ResourceBundle.getBundle("db");

    static  {

        if (rb == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            HBM2DDL_AUTO = "UNDEFINED";
            CURRENT_SESSION_CONTEXT_CLASS = "UNDEFINED";
            DRIVER = "com.mysql.cj.jdbc.Driver";
        } else {
            URL = rb.getString("url");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
            DRIVER = rb.getString("driver");
            HBM2DDL_AUTO = rb.getString("hibernate.hbm2ddl.auto");
            CURRENT_SESSION_CONTEXT_CLASS = rb.getString("hibernate.current_session_context_class");
        }
    }

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DRIVER);
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(USER);
        comboPooledDataSource.setPassword(PASSWORD);

        comboPooledDataSource.setMinPoolSize(3);
        comboPooledDataSource.setAcquireIncrement(5);
        comboPooledDataSource.setMaxPoolSize(20);
        comboPooledDataSource.setMaxStatements(180);
    }

    public static synchronized DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return comboPooledDataSource.getConnection();
    }

    public String getProperty(String key) {
        return rb.getString(key);
    }
}
