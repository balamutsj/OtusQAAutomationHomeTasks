package pages;

import com.google.inject.Inject;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Injector;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(), 'Авторские онлайн‑курсы для')]")
    WebElement mainPageTitle;
    @FindBy(css = ".header2__auth")
    WebElement mainPageHeaderAuthBtn;
    @FindBy(xpath = "//div[@class='subtitle-new']")
    List<WebElement> subTitles;

    @Inject
    public MainPage(Injector injector) {
        super(injector);
    }

    @Inject
    HeaderMenu headerMenu;

    private final Logger logger = LogManager.getLogger(MainPage.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    public MainPage openMainPage() {
        action.openPage(cfg.baseUrl(), mainPageTitle);
        logger.info("Главная страница Отус открыта");
        return this;
    }

    public MainPage isAtMainPage() {
        action.isAt(mainPageTitle);
        logger.info("На главной странице Отус");
        return this;
    }

    public MainPage isAtMainPageAuth() {
        action.isAt(mainPageTitle);
        action.waitElemIsDisplayed(headerMenu.getUserMenuMainItem());
        logger.info("На главной странице Отус");
        return this;
    }

    public AuthPage goToAuthPage() {
        action.clickOnVisibleElement(mainPageHeaderAuthBtn);
        logger.info("Кнопка 'Вход/Регистрация' нажата");
        return new AuthPage(injector);
    }

    public List<String> collectHeaderMenuItems() {
        List<WebElement> menuItemElements = headerMenu.getHeaderMenuItem();
        List<String> menuItemsText = new ArrayList<>();
        for(WebElement element: menuItemElements) {
            menuItemsText.add(element.getText());
        }
        logger.info("Данные по названиям пунктов меню получены");
        return menuItemsText;
    }

    public List<String> collectPageSubTitlesItems() {
        List<WebElement> subTitles = this.subTitles;
        List<String> subTitlesText = new ArrayList<>();
        for(WebElement element: subTitles) {
            subTitlesText.add(element.getText());
        }
        logger.info("Данные по названиям разделов получены");
        return subTitlesText;
    }

}
