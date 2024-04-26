package ge.tbcitacademy.data;
import ge.tbcitacademy.configs.MSSQLConnection;
import java.sql.*;

public class DatabaseSteps {
//    public ResultSet selectAllFromRegistrationData(Connection conn) throws SQLException {
//        String SQL = "SELECT * FROM RegistrationData";
//        Statement statement = conn.createStatement();
//        return statement.executeQuery(SQL);
//    }
    public DatabaseSteps createPhoneTable(){
        try(Connection conn = MSSQLConnection.connect()) {
            String createPhonesQuery = "DROP TABLE if exists Phones;" +
                    "CREATE TABLE Phones (" +
                    "phoneNumber VARCHAR(20) PRIMARY KEY, " +
                    "ownerId INT, " +
                    "CONSTRAINT FK_Phones_RegistrationData FOREIGN KEY (ownerId) REFERENCES RegistrationData(id)" +
                    ")";
            Statement statement = conn.createStatement();
            statement.execute(createPhonesQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public DatabaseSteps insertRegistrations() {
        String SQL= """
                    INSERT INTO RegistrationData values
                        (1006, 'Bobby', 'Green', 'male', 'Mega 123 Large screen', 'randomadress1', 'randomadress2', 'Batumi', '3413123', '3413123'),
                        (1007, 'Genadi', 'Kvikvinia', 'male', 'Serene Pad 64G', 'randomadress3', 'randomadress4', 'Batumi', '3413123', '3413123'),
                        (1008, 'Levan', 'Saginashvili', 'male', 'Mega 123 Large screen', 'randomadress5', 'randomadress6', 'Batumi', '3413123', '3413123'),
                        (1009, 'SomeName', 'SomeSurname', 'male', 'Mega 123 Medium screen', 'randomadress7', 'randomadress8', 'Batumi', '3413123', '3413123'),
                        (1010, 'TBC', 'BANK', 'male', 'Mega 123 Medium screen', 'randomadress9', 'someaddress10', 'Batumi', '3413123', '3413123');
                """;
        try (Connection conn = MSSQLConnection.connect()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public DatabaseSteps updateRegistration(String firstName, String lastName, int updateWithId) {
        String updateSQL = "UPDATE RegistrationData SET firstName = ?, lastName = ? WHERE id = ?";
        try (Connection conn = MSSQLConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, updateWithId);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public DatabaseSteps insertPhoneNumber(String number, int id)   {
        String insertSQL = "INSERT INTO Phones (phoneNumber, ownerId) VALUES (?, ?)";
        try (Connection conn = MSSQLConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                pstmt.setString(1, number);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public DatabaseSteps updatePhoneNumber(String number, int updateWithId)   {
        String updateSQL = "UPDATE Phones SET phoneNumber = ? WHERE ownerId = ?";
        try (Connection conn = MSSQLConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);

            pstmt.setString(1, number);
            pstmt.setInt(2, updateWithId);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public int getStartingRowCount() {
            try {
                Connection connection = MSSQLConnection.connect();
                Statement statement = connection.createStatement();
                String query = "SELECT COUNT(*) FROM RegistrationData";

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public void insertIntoRegistrationWithoutCommit(Connection connection) throws SQLException {
        String SQL= """
                    INSERT INTO RegistrationData values
                        (7777, 'Bobby', 'Green', 'male', 'Mega 123 Large screen', 'randomadress1', 'randomadress2', 'Batumi', '3413123', '3413123');
                """;
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL);
    }
    public int getFirstId() {
        String sql = "SELECT * FROM RegistrationData";
        try (Connection conn1 = MSSQLConnection.connect()) {
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public void updateLastNameUsingId(int registrationId, String newLastName)  {
        String query = "UPDATE RegistrationData SET lastName = ? WHERE id = ?";
        try (Connection connection = MSSQLConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newLastName);
            statement.setInt(2, registrationId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getLastNameById(int registrationId) {
        String query = "SELECT lastName FROM RegistrationData WHERE id = ?";

        try (Connection connection = MSSQLConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, registrationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("lastName");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
