package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage{

    @FindBy(xpath = "//div//span[contains(text(), 'Войдите в свой аккаунт')]")
    WebElement authPageLoginTitle;
    @FindBy(css = "[action=\"/login/\" ] input[placeholder=\"Электронная почта\"]")
    WebElement authPageLoginInput;
    @FindBy(css = "[action=\"/login/\" ] input[placeholder=\"Введите пароль\"]")
    WebElement authPagePasswordInput;
    @FindBy(css = "[action=\"/login/\"] button")
    WebElement authPageLoginBtn;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    private final Logger logger = LogManager.getLogger(AuthPage.class);

    @Step()
    public AuthPage isAtAuthPage() {
        action.isAt(authPageLoginTitle);
        logger.info("На странице аутентификации");
        return this;
    }

    @Step()
    public AuthPage fillEmailField() {
        String login = null;
        String actualLogin;
        try {
           login = System.getProperty("login").toLowerCase();
        } catch (Exception e) {

        }
        if(login!= null) {
            actualLogin = login;
        } else {
            actualLogin = cfg.userEmail();
        }
        action.typeText(authPageLoginInput, actualLogin);
        logger.info("Поле Почта заполнено");
        return this;
    }

    @Step()
    public AuthPage fillPasswordField() {
        String password = null;
        String actualPassword;
        try {
            password = System.getProperty("password").toLowerCase();
        } catch (Exception e) {

        }
        if(password!= null) {
            actualPassword = password;
        } else {
            actualPassword = cfg.userPassword();
        }
        action.typeText(authPagePasswordInput, actualPassword);
        logger.info("Поле Пароль заполнено");
        return this;
    }

    @Step()
    public MainPage clickLogInButton() {
        action.clickOnVisibleElement(authPageLoginBtn);
        logger.info("Кнопка 'Войти' нажата");
        return new MainPage(driver);
    }

}
