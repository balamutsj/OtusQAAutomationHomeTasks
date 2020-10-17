import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class HomeTask04simpleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask04simpleTest.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Test
    @DisplayName("isAtOtusPage")
    void isAtOtusPage(TestInfo testInfo) {
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver = WebDriverFactory.createDriver(BrowserType.firefox);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");

        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        String currentPageTitle = driver.getTitle();
        logger.info("Текущий заголовок страницы: " + currentPageTitle);

        assert currentPageTitle.contains(expectedPageTitle);
    }

    @Test
    @DisplayName("isAtOtusPage")
    void isAtOtusPageWithOptions(TestInfo testInfo) {
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = WebDriverFactory.createDriver(BrowserType.chrome, options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");

        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        String currentPageTitle = driver.getTitle();
        logger.info("Текущий заголовок страницы: " + currentPageTitle);

        assert currentPageTitle.contains(expectedPageTitle);
    }

    @AfterEach
    public void setDown() {
        logger.info("=====тест завершился=====");
        if (driver != null) {
            driver.quit();
        }
    }
}
