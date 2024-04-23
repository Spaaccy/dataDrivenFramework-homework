package ge.tbcitacademy.data;
import ge.tbcitacademy.configs.MSSQLConnection;
import java.sql.*;

public class DatabaseSteps {
    public ResultSet selectAllFromRegistrationData() throws SQLException {
        Connection connection = MSSQLConnection.connect();
        String SQL = "SELECT * FROM RegistrationData";
        Statement statement = connection.createStatement();
        return statement.executeQuery(SQL);
    }
}
