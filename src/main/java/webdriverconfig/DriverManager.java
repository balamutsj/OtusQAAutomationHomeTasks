package webdriverconfig;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    public abstract WebDriver getDriver();
    public abstract WebDriver getCustomDriver(MutableCapabilities caps);

    public void stopDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
