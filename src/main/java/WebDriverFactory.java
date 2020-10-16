import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver createDriver(DriverType driverType, MutableCapabilities caps) {
        WebDriver driver = null;
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(caps);
                break;
            /*case EDGE:
                driverManager = new MicrosoftEdgeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FireFoxDriverManager();
                break;
                break;*/
            default:

                break;
        }
        return driver;
    }

    public static WebDriver createDriver(DriverType driverType) {
        WebDriver driver = null;
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            /*case EDGE:
                driverManager = new MicrosoftEdgeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FireFoxDriverManager();
                break;*/
            default:

                break;
        }
        return driver;
    }
}