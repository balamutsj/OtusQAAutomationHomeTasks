package pages;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    WebDriver driver;
    UserActions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new UserActions(driver);
    }

    protected ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
}
