package utils;

import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import webdriverconfig.DriverType;
import webdriverconfig.WebDriverFactory;

@ScenarioScoped
public class Injector {
    public WebDriver driver = WebDriverFactory.createDriver(DriverType.CHROME).getDriver();
}
