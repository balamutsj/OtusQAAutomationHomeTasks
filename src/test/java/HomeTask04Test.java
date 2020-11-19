import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webdriverconfig.DriverManager;
import webdriverconfig.DriverType;
import webdriverconfig.WebDriverFactory;

public class HomeTask04Test {

    WebDriver driver;
    DriverManager driverManager;

    private Logger logger = LogManager.getLogger(HomeTask04Test.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Test
    @DisplayName("isAtOtusPageBaseChrome")
    void isAtOtusPageBaseChrome() {

        driverManager = WebDriverFactory.createDriver(DriverType.CHROME);
        driver = driverManager.getDriver();

        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        String currentPageTitle = driver.getTitle();
        logger.info("Текущий заголовок страницы: " + currentPageTitle);

        assert currentPageTitle.contains(expectedPageTitle);

        driverManager.stopDriver(driver);
    }

    @Test
    @DisplayName("isAtOtusPageCustomChrome")
    void isAtOtusPageCustomChrome() {

        WebDriver driver;
        DriverManager driverManager;
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driverManager = WebDriverFactory.createDriver(DriverType.CHROME);
        driver = driverManager.getCustomDriver(options);

        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        String currentPageTitle = driver.getTitle();
        logger.info("Текущий заголовок страницы: " + currentPageTitle);

        assert currentPageTitle.contains(expectedPageTitle);

        driverManager.stopDriver(driver);
    }

}
