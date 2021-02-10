import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    private Logger logger = LogManager.getLogger(BaseTest.class);

    @Step("Chrome driver setup")
    @BeforeEach
    private void setUp(TestInfo testInfo) {
        driver = WebDriverFactory.createDriver(BrowserType.chrome);
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @Step("Chrome driver stop")
    @AfterEach
    private void setDown() {
        takeScreenShot(driver);
        logger.info("=====тест завершился=====");
        if (driver != null) {
            driver.quit();
        }
        logger.info("Драйвер остановлен");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] takeScreenShot (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
