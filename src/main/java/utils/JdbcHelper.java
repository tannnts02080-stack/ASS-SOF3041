package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHelper {

    private static final String URL =
        "jdbc:sqlserver://DESKTOP-7E01UMT:1433;"
      + "databaseName=ABCNews;"
      + "encrypt=false;";

    private static final String USER = "sa";
    private static final String PASS = "123456";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println(">>> JDBC Driver loaded OK!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
