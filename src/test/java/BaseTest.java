import config.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
