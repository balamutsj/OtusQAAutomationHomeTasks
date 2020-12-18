package webdriverconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverFactory {

    public static DriverManager createDriver(DriverType driverType) {

        DriverManager driverManager = null;
        Logger logger = LogManager.getLogger(WebDriverFactory.class);

        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                logger.info("ChromeDriver is ready");
                break;
            case FIREFOX:
                driverManager = new FireFoxDriverManager();
                logger.info("FireFoxDriver is ready");
                break;
            default:
                logger.info("No driver was set");
                break;
        }
        return driverManager;
    }
}
