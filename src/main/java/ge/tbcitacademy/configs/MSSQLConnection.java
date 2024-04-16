package ge.tbcitacademy.configs;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLConnection {
    public static Connection connect() {
        try {
            DriverManager.registerDriver(new SQLServerDriver());

            String jdbcUrl = DBConfig.getURL();
            String password = DBConfig.getPassword();
            String username = DBConfig.getUsername();

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}