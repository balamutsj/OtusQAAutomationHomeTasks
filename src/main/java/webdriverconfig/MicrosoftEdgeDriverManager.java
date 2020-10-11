package webdriverconfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MicrosoftEdgeDriverManager extends DriverManager {

    public WebDriver createDriver() {
        WebDriverManager.edgedriver().setup();
        return new FirefoxDriver();
    }
}
