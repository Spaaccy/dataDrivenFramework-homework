package ge.tbcitacademy.configs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;
import static ge.tbcitacademy.data.TechCanvasConstants.TECH_CANVAS_URL;

public class BaseConfig {
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        open();
        open(TECH_CANVAS_URL);
        WebDriverManager.chromedriver().setup();
        webdriver().driver().getWebDriver().manage().window().maximize();
        timeout = 10000;
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        closeWebDriver();
    }
}