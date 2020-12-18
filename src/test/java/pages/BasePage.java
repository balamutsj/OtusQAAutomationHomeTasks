package pages;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.PageFactory;
import utils.UserActions;
import utils.Injector;

public abstract class BasePage {

    protected Injector injector;
    protected UserActions action;

    public BasePage(Injector injector) {
        this.injector = injector;
        PageFactory.initElements(injector.driver, this);
        action = new UserActions(injector.driver);
    }

    protected ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
}
