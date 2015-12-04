package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 11656 on 2015/10/30.
 */
public class DBUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/MRIMS";
    private static final String database_username = "root";
    private static final String database_password = "1358";
    private static final String database_charset = "utf8";
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnect() throws SQLException {
        Connection conn =null;
        conn = DriverManager.getConnection(URL, database_username, database_password);
        return conn;
    }

}
