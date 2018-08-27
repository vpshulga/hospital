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

    private final String URL;
    private final String DRIVER;
    private final String USER;
    private final String PASSWORD;

    {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        if (rb == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            DRIVER = "com.mysql.cj.jdbc.Driver";
        } else {
            URL = rb.getString("url");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
            DRIVER = rb.getString("driver");
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
}
