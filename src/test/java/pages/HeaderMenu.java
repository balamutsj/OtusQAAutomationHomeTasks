package pages;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import utils.Injector;

import java.util.List;

public class HeaderMenu extends BasePage{

    @FindBy(xpath = "//div[@class='header2-menu']")
    WebElement userMenuMainItem;
    @FindBy(xpath = "//div[@class='header2-menu header2-menu_main']//p[@class='header2-menu__item-text']")
    List<WebElement> headerMenuItem;
    @FindBy(css = "a[title=\"Личный кабинет\"]")
    WebElement userMenuPersonalAreaItem;

    @Inject
    public HeaderMenu(Injector injector) {
        super(injector);
    }

    public WebElement getUserMenuMainItem() {
        return userMenuMainItem;
    }

    public List<WebElement> getHeaderMenuItem() {
        return headerMenuItem;
    }

    private final Logger logger = LogManager.getLogger(HeaderMenu.class);

    public PersonalAreaPage goToPersonalArea() {
        action.hoverOverElement(userMenuMainItem).clickOnVisibleElement(userMenuPersonalAreaItem);
        logger.info("Пункт меню 'личный кабинет' нажат");
        return new PersonalAreaPage(injector);
    }

}
