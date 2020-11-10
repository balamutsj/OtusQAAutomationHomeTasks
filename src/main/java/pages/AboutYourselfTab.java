package pages;

import Models.AboutYourselfModel;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;
//TODO think over redo as builder
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

    public AboutYourselfTab(WebDriver driver) {
        super(driver);
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

    public AboutYourselfTab fillFirstNameLatinField(String... name) {
        action.typeText(firstNameLatinInput, action.setDefaultValue(fakerEn.name().firstName(), name));
        logger.info("Поле Имя(Latin) заполнено");
        return this;
    }

    public AboutYourselfTab fillLastNameField(String... name) {
        action.typeText(lastNameInput, action.setDefaultValue(fakerRu.name().lastName(), name));
        logger.info("Поле Фамилия заполнено");
        return this;
    }

    public AboutYourselfTab fillLastNameLatinField(String... name) {
        action.typeText(lastNameLatinInput, action.setDefaultValue(fakerEn.name().lastName(), name));
        logger.info("Поле Фамилия(Latin) заполнено");
        return this;
    }

    public AboutYourselfTab fillBlogNameLatinField(String... name) {
        action.typeText(blogNameInput, action.setDefaultValue(fakerEn.name().username(), name));
        logger.info("Поле Имя в блоге заполнено");
        return this;
    }

    public AboutYourselfTab fillDateOfBirthField(String... dateOfBirth) {
        action.typeText(birthDateInput, action.setDefaultValue(fakerEn.date().birthday().toString(), dateOfBirth));//TODO здесь пока костыль, нужно придумать творческий подход к выводу даты рождения
        logger.info("Поле Дата рождения заполнено");
        return this;
    }

    public AboutYourselfTab setCountry(String name) {
        commonInfoSpecificDropDown(countrySelect, "Страна", "country", name);
        logger.info("Поле Страна заполнено");
        return this;
    }

    public AboutYourselfTab setCity(String name) {
        commonInfoSpecificDropDown(citySelect, "Город", "city", name);
        logger.info("Поле Город заполнено");
        return this;
    }

    public AboutYourselfTab setEnglishLevel(String name) {
        commonInfoSpecificDropDown(englishLevelSelect, "Уровень английского", "english_level", name);
        logger.info("Поле Уровень английского заполнено");
        return this;
    }

    //Пока методы заполнения контактов в таком формате, если руки доберуться придумаю более изящный вариант +
    // оказываются контакты после перезагрузки страницы сортируются по алфабиту, так что пока в тесте данные способов оплаты стоят в алфавитном порядке
    public AboutYourselfTab setFirstContact(String contactType, String contactName) {
        action.clickOnVisibleElement(customGetInTouchSelect(0))
            .waitElemIsDisplayed(driver.findElement(By.xpath("//label//input[@name='contact-0-service']//following::button[1]")))
            .clickOnVisibleElement(customGetInTouchSelect(0, contactType))
            .waitElemIsNotDisplayed(driver.findElement(By.xpath("//label//input[@name='contact-0-service']//following::button[1]")))
            .typeText(driver.findElement(By.xpath("//input[@name='contact-0-value']")), contactName);
        return this;
    }

    public AboutYourselfTab setSecondContact(String contactType, String contactName) {
        String wayOfComm = customGetInTouchSelect(0).getText();
        if(!wayOfComm.contains("Способ")) {
            addContactButton.click();
        }
        action.clickOnVisibleElement(customGetInTouchSelect(1))
            .waitElemIsDisplayed(driver.findElement(By.xpath("//label//input[@name='contact-1-service']//following::button[1]")))
            .clickOnVisibleElement(customGetInTouchSelect(1, contactType))
            .waitElemIsNotDisplayed(driver.findElement(By.xpath("//label//input[@name='contact-1-service']//following::button[1]")))
            .typeText(driver.findElement(By.xpath("//input[@name='contact-1-value']")), contactName);
        return this;
    }

    public PersonalAreaPage saveAndContinue() {
        action.clickOnVisibleElement(savaAndContinueButton);
        logger.info("Поле Уровень английского заполнено");
        return new PersonalAreaPage(driver);
    }

    public AboutYourselfModel getFormData() { //TODO методы сбора данных обернуть в try\catch
        AboutYourselfModel model = new AboutYourselfModel();
        model.setUserFirstNameRu(getFiledAttrValue(firstNameInput));
        model.setUserLastNameRu(getFiledAttrValue(lastNameInput));
        model.setUserFirstLatinName(getFiledAttrValue(firstNameLatinInput));
        model.setUserLastLatinName(getFiledAttrValue(lastNameLatinInput));
        model.setUserBlogName(getFiledAttrValue(blogNameInput));
        model.setUserBirthDate(getFiledAttrValue(birthDateInput));
        model.setUserCountry(countrySelect.getText());
        model.setUserCity(citySelect.getText());
        model.setUserEnglishLevel(englishLevelSelect.getText());
        model.setUserContact1(getFiledAttrValue(contactInput1));
        model.setUserContact2(getFiledAttrValue(contactInput2));
        return model;
    }

    /*
     * пока оставлю в этом классе, т.к. нет необхдимоси  выносить в useractions, метод написаны под текущий класс ,
     * а в useractions методы, которые буду переиспользовать в будущих домашках, если буду переиспользовать , то вынесу
     * */

    private String getFiledAttrValue(WebElement locator) {
        return locator.getAttribute("value");
    }

    private WebElement customGetInTouchSelect(Integer index) {
        return driver.findElement(By.xpath(String.format("//label//input[@name='contact-%s-service']//following::div[1]", index)));
    }

    private WebElement customGetInTouchSelect(Integer index, String name) {
        return driver.findElement(By.xpath(String.format("//label//input[@name='contact-%s-service']//following::button[@title='%s'][1]", index, name)));
    }

    private void commonInfoSpecificDropDown(WebElement dropDown, String xpathDropDownValue, String attrbValue, String dropDownListItemValue) {
        action.waitElemToBeClickable(dropDown).clickOnVisibleElement(dropDown);
        action.waitElemIsDisplayed(driver.findElement(By.xpath(String.format("//div//p[contains(text(),'%s')]//following::button[1]", xpathDropDownValue))));
        driver.findElement(By.cssSelector(String.format("button[title=\"%s\"]", dropDownListItemValue))).click();
        action.waitElemIsNotDisplayed(driver.findElement(By.cssSelector("button[title=\"Не выбрано\"]")));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.xpath(String.format("//label//input[@name='%s']", attrbValue))), "value"));
    }
}
