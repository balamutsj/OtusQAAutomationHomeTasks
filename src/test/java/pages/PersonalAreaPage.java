package pages;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Injector;

public class PersonalAreaPage extends BasePage{
    @FindBy(xpath = "//h1[text()='Личный кабинет']")
    WebElement personalAreaPageHeader;
    @FindBy(xpath = "//div[@class=\"nav__items\"]//a[@href=\"/lk/biography/personal/\"]")
    WebElement personalAreaAboutYselfTabMenuItem;

    @FindBy(xpath = "//span[text()='Данные успешно сохранены']")
    WebElement successMessage;

    @Inject
    public PersonalAreaPage(Injector injector) {
        super(injector);
    }

    private final Logger logger = LogManager.getLogger(PersonalAreaPage.class);

    public PersonalAreaPage isAtPersonalAreaPage() {
        action.isAt(personalAreaPageHeader);
        logger.info("На странице Личный кабинет");
        return this;
    }

    public PersonalAreaPage assertDataBeenSaved() {
        action.waitElemIsDisplayed(successMessage);
        logger.info("Данные успешно сохранены");
        return this;
    }

    public AboutYourselfTab goToAboutYourselfTab() {
        action.clickOnVisibleElement(personalAreaAboutYselfTabMenuItem);
        logger.info("Пункт меню 'О себе' нажат");
        return new AboutYourselfTab(injector);
    }
}
