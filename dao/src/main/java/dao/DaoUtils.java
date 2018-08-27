package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {
    public static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
