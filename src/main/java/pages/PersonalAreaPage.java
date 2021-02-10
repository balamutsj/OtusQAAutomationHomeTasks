package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAreaPage extends BasePage{

    @FindBy(xpath = "//h1[text()='Личный кабинет']")
    WebElement personalAreaPageHeader;
    @FindBy(xpath = "//div[@class=\"nav__items\"]//a[@href=\"/lk/biography/personal/\"]")
    WebElement personalAreaAboutYselfTabMenuItem;

    @FindBy(xpath = "//span[text()='Данные успешно сохранены']")
    WebElement successMessage;

    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }

    private final Logger logger = LogManager.getLogger(PersonalAreaPage.class);

    @Step()
    public PersonalAreaPage isAtPersonalAreaPage() {
        action.isAt(personalAreaPageHeader);
        logger.info("На странице Личный кабинет");
        return this;
    }

    @Step()
    public PersonalAreaPage assertDataBeenSaved() {
        action.waitElemIsDisplayed(successMessage);
        logger.info("Данные успешно сохранены");
        return this;
    }

    @Step()
    public AboutYourselfTab goToAboutYourselfTab() {
        action.clickOnVisibleElement(personalAreaAboutYselfTabMenuItem);
        logger.info("Пункт меню 'О себе' нажат");
        return new AboutYourselfTab(driver);
    }
}
