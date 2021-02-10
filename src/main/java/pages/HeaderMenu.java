package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends BasePage{

    @FindBy(xpath = "//div[@class='header2-menu']")
    WebElement userMenuMainItem;
    @FindBy(css = "a[title=\"Личный кабинет\"]")
    WebElement userMenuPersonalAreaItem;

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    private final Logger logger = LogManager.getLogger(HeaderMenu.class);

    @Step()
    public PersonalAreaPage goToPersonalArea() {
        action.hoverOverElement(userMenuMainItem).clickOnVisibleElement(userMenuPersonalAreaItem);
        logger.info("Пункт меню 'личный кабинет' нажат");
        return new PersonalAreaPage(driver);
    }
}
