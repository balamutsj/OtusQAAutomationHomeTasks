import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import webdriverconfig.DriverManager;
import webdriverconfig.DriverType;
import webdriverconfig.WebDriverFactory;

public class BaseTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        DriverManager driverManager = WebDriverFactory.createDriver(DriverType.CHROME);
        driver = driverManager.getDriver();
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @AfterEach
    public void setDown() {
        logger.info("=====тест завершился=====");
        if (driver != null) {
            driver.quit();
        }
    }
}
