package pages;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Injector;

public class RestorePassModal extends BasePage{

    @FindBy(xpath = "//p[text()='Восстановление пароля']")
    WebElement modalHeader;
    @FindBy(xpath = "//div[@class='modal new-log-reg__popup']//input[@placeholder='Электронная почта']")
    WebElement emailInput;
    @FindBy(xpath = "//button[contains(text(), 'Восстановить')]")
    WebElement submitBtn;
    @FindBy(xpath = "//span[contains(text(), 'Пользователь с таким email не найден')]")
    WebElement errorMsg;
    @FindBy(xpath = "//h3[contains(text(), 'Письмо с ссылкой для восстановление пароля отправлено на почту')]")
    WebElement successPopUp;


    @Inject
    public RestorePassModal(Injector injector) {
        super(injector);
    }

    private final Logger logger = LogManager.getLogger(RestorePassModal.class);

    public RestorePassModal isAtRestorePassModal() {
        action.isAt(modalHeader);
        logger.info("Модальное окно - Восстановить пароль");
        return this;
    }

    public RestorePassModal fillEmailField(String email) {
        action.typeText(emailInput, email);
        logger.info("Email введен");
        return this;
    }

    public RestorePassModal clickRestorePass() {
        action.clickOnVisibleElement(submitBtn);
        logger.info("Кнопка Восстановить нажата");
        return this;
    }

    public RestorePassModal assertEmailSent() {
        action.waitElemIsDisplayed(successPopUp);
        logger.info("Email успешно отправлен");
        return this;
    }

    public String getErrorMsgText() {
        return action.getElementText(errorMsg);
    }
}
