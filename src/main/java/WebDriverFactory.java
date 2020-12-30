import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class WebDriverFactory {

    public static WebDriver createDriver(BrowserType browserType) throws MalformedURLException {
        WebDriver driver = null;
        String switchBrowserValue;
        String testBrowserValue = browserType.getBrowser();
        String mavenBrowserValue = null;
        try {
            mavenBrowserValue = System.getProperty("browser").toLowerCase();
        } catch (Exception e) {

        }
        if(mavenBrowserValue!= null) {
            switchBrowserValue = mavenBrowserValue;
        } else {
            switchBrowserValue = testBrowserValue;
        }
        switch (switchBrowserValue) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "remote":
                WebDriverManager.firefoxdriver().setup();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "87.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);

                driver = new RemoteWebDriver(
                        URI.create("http://localhost:4444/wd/hub").toURL(),
                        capabilities
                );
                break;
            default:

                break;
        }
        return driver;
    }

    public static WebDriver createDriver(BrowserType browserType, MutableCapabilities caps) {
        WebDriver driver = null;
        String switchBrowserValue;
        String testBrowserValue = browserType.getBrowser();
        String mavenBrowserValue = null;
        try {
            mavenBrowserValue = System.getProperty("browser").toLowerCase();
        } catch (Exception e) {

        }
        if(mavenBrowserValue!= null) {
            switchBrowserValue = mavenBrowserValue;
        } else {
            switchBrowserValue = testBrowserValue;
        }
        switch (switchBrowserValue) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(caps);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(caps);
                break;
            default:

                break;
        }
        return driver;
    }
}