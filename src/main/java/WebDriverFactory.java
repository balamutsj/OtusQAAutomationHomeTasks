import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createDriver(BrowserType browserType) {
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