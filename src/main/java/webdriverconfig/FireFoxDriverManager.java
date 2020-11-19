package webdriverconfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxDriverManager extends DriverManager {
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        return new FirefoxDriver(options);
    }

    public WebDriver getCustomDriver(MutableCapabilities caps) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(caps);
    }
}
