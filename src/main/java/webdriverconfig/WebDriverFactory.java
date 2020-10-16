package webdriverconfig;

public class WebDriverFactory {

    public static DriverManager createDriver(DriverType driverType) {

        DriverManager driverManager = null;

        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
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
        return driverManager;
    }
}
