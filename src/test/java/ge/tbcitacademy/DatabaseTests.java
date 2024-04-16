package ge.tbcitacademy;
import ge.tbcitacademy.configs.BaseConfig;
import ge.tbcitacademy.data.DatabaseSteps;
import ge.tbcitacademy.steps.TechRegisterPageSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseTests extends BaseConfig {
    DatabaseSteps databaseSteps;
    TechRegisterPageSteps techRegisterPageSteps;

    @BeforeClass
    public void beforeClass() {
        databaseSteps = new DatabaseSteps();
        techRegisterPageSteps = new TechRegisterPageSteps();
    }

    @Test
    public void testDb() {
        try(ResultSet resultSet = databaseSteps.selectAllFromRegistrationData()) {
            while (resultSet.next()) {
                techRegisterPageSteps
                        .fillUsername(resultSet.getString("firstName"))
                        .fillLastName(resultSet.getString("lastName"))
                        .selectGender(resultSet.getString("gender"))
                        .selectModel(resultSet.getString("model"))
                        .fillAddress1(resultSet.getString("address1"))
                        .fillAddress2(resultSet.getString("address2"))
                        .fillCity(resultSet.getString("city"))
                        .fillContact1(resultSet.getString("contact1"))
                        .fillContact2(resultSet.getString("contact2"))
                        .refreshPage();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
