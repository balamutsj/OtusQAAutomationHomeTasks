package pages;

import com.github.javafaker.Faker;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Injector;

import java.util.Locale;

public class AboutYourselfTab extends BasePage{

    @FindBy(css = "div[class=hide-sm] h3")
    WebElement personalDataTabTitle;
    @FindBy(css = "#id_fname")
    WebElement firstNameInput;
    @FindBy(css = "#id_fname_latin")
    WebElement firstNameLatinInput;
    @FindBy(css = "#id_lname")
    WebElement lastNameInput;
    @FindBy(css = "#id_lname_latin")
    WebElement lastNameLatinInput;
    @FindBy(css = "#id_blog_name")
    WebElement blogNameInput;
    @FindBy(css = "[name=\"date_of_birth\"]")
    WebElement birthDateInput;
    @FindBy(xpath = "//div//p[contains(text(),'Страна')]//following::div[1]")
    WebElement countrySelect;
    @FindBy(xpath = "//div//p[contains(text(),'Город')]//following::div[1]")
    WebElement citySelect;
    @FindBy(xpath = "//div//p[contains(text(),'Уровень английского')]//following::div[1]")
    WebElement englishLevelSelect;
    @FindBy(xpath = "//input[@name='contact-0-value']")
    WebElement contactInput1;
    @FindBy(xpath = "//input[@name='contact-1-value']")
    WebElement contactInput2;
    @FindBy(xpath = "//button[text()='Добавить']")
    WebElement addContactButton;
    @FindBy(css = "[title=\"Сохранить и продолжить\"]")
    WebElement savaAndContinueButton;

    @Inject
    public AboutYourselfTab(Injector injector) {
        super(injector);
    }

    private final Logger logger = LogManager.getLogger(AboutYourselfTab.class);

    Faker fakerRu = new Faker(new Locale("ru-RU"));
    Faker fakerEn = new Faker(new Locale("en-GB"));

    public AboutYourselfTab isAtAboutYourselfTab() {
        action.isAt(personalDataTabTitle);
        logger.info("На странице Личный кабинет: О себе");
        return this;
    }

    public AboutYourselfTab fillFirstNameField(String... name) {
        action.typeText(firstNameInput, action.setDefaultValue(fakerRu.name().firstName(), name));
        logger.info("Поле Имя заполнено");
        return this;
    }

    public AboutYourselfTab fillLastNameField(String... name) {
        action.typeText(lastNameInput, action.setDefaultValue(fakerRu.name().lastName(), name));
        logger.info("Поле Фамилия заполнено");
        return this;
    }

    public AboutYourselfTab fillBlogNameLatinField(String... name) {
        action.typeText(blogNameInput, action.setDefaultValue(fakerEn.name().username(), name));
        logger.info("Поле Имя в блоге заполнено");
        return this;
    }
}
