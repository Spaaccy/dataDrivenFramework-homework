package ge.tbcitacademy;
import ge.tbcitacademy.configs.MSSQLConnection;
import ge.tbcitacademy.data.DatabaseSteps;
import ge.tbcitacademy.steps.TechRegisterPageSteps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseTests {
    DatabaseSteps databaseSteps;
    TechRegisterPageSteps techRegisterPageSteps;
    SoftAssert softAssert;

    @BeforeClass
    public void beforeClass() {
        databaseSteps = new DatabaseSteps();
        techRegisterPageSteps = new TechRegisterPageSteps();
        softAssert = new SoftAssert();
    }

    @Test
    public void testDb1() {
        int firstRowId = databaseSteps.getFirstId();
        databaseSteps
                    .createPhoneTable()
                    .insertRegistrations()
                    .insertPhoneNumber("38274823", firstRowId)
                    .insertPhoneNumber("124134123", firstRowId+1)
                    .insertPhoneNumber("5435123", firstRowId+2)
                    .insertPhoneNumber("567123", firstRowId+3)
                    .insertPhoneNumber("651231", firstRowId+4)
                    .insertPhoneNumber("5714414", firstRowId+5)
                    .insertPhoneNumber("3131413", firstRowId+6)
                    .insertPhoneNumber("53727631", firstRowId+7)
                    .updateRegistration("yo updated","lastname",firstRowId+6)
                    .updateRegistration("yo updated","lastname",firstRowId+7)
                    .updatePhoneNumber("77777777", firstRowId)
                    .updatePhoneNumber("66666666", firstRowId+2);

    }
    @Test(dataProvider = "userData", dataProviderClass = ge.tbc.tbcacademy.data.DataProviders.class)
    public void verifyUserData(String firstName, String lastName, String phoneNumber) {
        System.out.println(firstName + " " + lastName + " " + phoneNumber);
    }
    @Test
    public void testAutoCommitFalse() {
        int startingRowCount = databaseSteps.getStartingRowCount();
        try {
            Connection connection = MSSQLConnection.connect();
            connection.setAutoCommit(false);
            databaseSteps.insertIntoRegistrationWithoutCommit(connection);
            // screenshot before commit, uploaded in classroom (beforecommit.jpeg)
            connection.commit();
            // close if even if i dont commit
            connection.close();
            // screenshot after commit, uploaded in classroom (aftercommit.jpeg)
            softAssert.assertEquals(startingRowCount + 1, databaseSteps.getStartingRowCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testLastNameUpdate() {
        databaseSteps.updateLastNameUsingId(1005, "Gigglepot2");
        Assert.assertEquals(databaseSteps.getLastNameById(1005), "Gigglepot2");
    }
    @AfterClass
    public void assertAll() {
        softAssert.assertAll();
    }
}
