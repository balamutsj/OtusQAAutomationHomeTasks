package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(), 'Авторские онлайн‑курсы для')]")
    WebElement mainPageTitle;
    @FindBy(css = ".header2__auth")
    WebElement mainPageHeaderAuthBtn;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final Logger logger = LogManager.getLogger(MainPage.class);

    public MainPage openMainPage() {
        action.openPage(cfg.url(), mainPageTitle);
        logger.info("Главная страница Отус открыта");
        return this;
    }

    public MainPage isAtMainPage() {
        action.isAt(mainPageTitle);
        logger.info("На главной странице Отус");
        return this;
    }

    public AuthPage goToAuthPage() {
        action.clickOnVisibleElement(mainPageHeaderAuthBtn);
        logger.info("Кнопка 'Вход/Регистрация' нажата");
        return new AuthPage(driver);
    }
}
