import config.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeTask02Test {

    public static WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask02Test.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }
    @Test
    @DisplayName("User is on Otus Main page")
    void isAtOtusPage() {
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

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
