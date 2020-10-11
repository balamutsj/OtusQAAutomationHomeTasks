package webdriverconfig;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    public abstract WebDriver createDriver();

    public void stopDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
