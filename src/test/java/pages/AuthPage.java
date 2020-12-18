package pages;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Injector;

public class AuthPage extends BasePage{

    @FindBy(xpath = "//div//span[contains(text(), 'Войдите в свой аккаунт')]")
    WebElement authPageLoginTitle;
    @FindBy(css = "[action=\"/login/\" ] input[placeholder=\"Электронная почта\"]")
    WebElement authPageLoginInput;
    @FindBy(css = "[action=\"/login/\" ] input[placeholder=\"Введите пароль\"]")
    WebElement authPagePasswordInput;
    @FindBy(css = "[action=\"/login/\"] button")
    WebElement authPageLoginBtn;
    @FindBy(xpath = "//div[contains(text(), 'Такая пара логин/пароль не существует')]")
    WebElement errorMsg;
    @FindBy(xpath = "//a[@title='Забыли пароль?']")
    WebElement forgotPassLink;

    @Inject
    public AuthPage(Injector injector) {
        super(injector);
    }

    private final Logger logger = LogManager.getLogger(AuthPage.class);

    public AuthPage isAtAuthPage() {
        action.isAt(authPageLoginTitle);
        logger.info("На странице аутентификации");
        return this;
    }

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

    public AuthPage fillEmailField(String email) {
        action.typeText(authPageLoginInput, email);
        logger.info("Поле Почта заполнено");
        return this;
    }

    public AuthPage fillPasswordField(String password) {
        action.typeText(authPagePasswordInput, password);
        logger.info("Поле Пароль заполнено");
        return this;
    }

    public AuthPage fillCredentials(String email, String password) {
        action.typeText(authPageLoginInput, email);
        action.typeText(authPagePasswordInput, password);
        logger.info("Поля Логин и Пароль заполнены");
        return this;
    }

    public MainPage clickLogInButton() {
        action.clickOnVisibleElement(authPageLoginBtn);
        logger.info("Кнопка 'Войти' нажата");
        return new MainPage(injector);
    }

    public RestorePassModal clickForgotPassLink() {
        action.clickOnVisibleElement(forgotPassLink);
        logger.info("Ссылка Забыли пароль нажата");
        return new RestorePassModal(injector);
    }

    public String getErrorMsgText() {
        return action.getElementText(errorMsg);
    }

}
