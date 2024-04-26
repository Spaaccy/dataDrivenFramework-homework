package ge.tbc.tbcacademy.data;

import ge.tbcitacademy.configs.MSSQLConnection;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProviders {
    @DataProvider(name = "userData")
    public static Object[][] databaseProvider(){
        try (Connection connection = MSSQLConnection.connect()){
            String selectSQL = "SELECT rd.firstName, rd.lastName, p.phoneNumber " +
                    "FROM RegistrationData rd INNER JOIN Phones p ON rd.id = p.ownerId";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(selectSQL);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            Object[][] data = new Object[rowCount][resultSet.getMetaData().getColumnCount()];

            int iter = 0;
            while (resultSet.next()){
                data[iter] = new Object[] {
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                };
                iter++;
            }
            return data;

        } catch (SQLException e) {
            return new Object[][]{};
        }
    }
}