import Models.AboutYourselfModel;
import com.github.javafaker.Faker;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeTask10PageObjectTest {

    protected WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask10PageObjectTest.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Test
    void otusUserInfoTest() throws InterruptedException {
        //Prepare
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));
        MainPage mainPage = new MainPage(driver);
        AuthPage authPage = new AuthPage(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        AboutYourselfTab aboutYourselfTab = new AboutYourselfTab(driver); //TODO research on PODAM library
        AboutYourselfModel expData = new AboutYourselfModel();
        expData.setUserFirstNameRu(fakerRu.name().firstName());
        expData.setUserLastNameRu(fakerRu.name().lastName());
        expData.setUserFirstLatinName(fakerEn.name().firstName());
        expData.setUserLastLatinName(fakerEn.name().lastName());
        expData.setUserBlogName(fakerEn.name().username());
        expData.setUserBirthDate("11.11.2000");
        expData.setUserCountry("Россия");
        expData.setUserCity("Москва");
        expData.setUserEnglishLevel("Средний (Intermediate)");
        expData.setUserContact1(fakerEn.name().username());
        expData.setUserContact2(fakerEn.name().username());
        //Act
        mainPage.openMainPage()
                .goToAuthPage();
        authPage.fillEmailField()
                .fillPasswordField()
                .clickLogInButton();
        mainPage.isAtMainPage();
        headerMenu.goToPersonalArea();
        personalAreaPage.isAtPersonalAreaPage()
                .goToAboutYourselfTab();
        aboutYourselfTab.fillFirstNameField(expData.getUserFirstNameRu())
                .fillLastNameField(expData.getUserLastNameRu())
                .fillFirstNameLatinField(expData.getUserFirstLatinName())
                .fillLastNameLatinField(expData.getUserLastLatinName())
                .fillBlogNameLatinField(expData.getUserBlogName())
                .fillDateOfBirthField(expData.getUserBirthDate())
                .setCountry(expData.getUserCountry())
                .setCity(expData.getUserCity())
                .setEnglishLevel(expData.getUserEnglishLevel())
                .setFirstContact("Skype", expData.getUserContact1())
                .setSecondContact("VK", expData.getUserContact2())
                .saveAndContinue();
        personalAreaPage.isAtPersonalAreaPage()
                .assertDataBeenSaved();
        clearUpCookies(driver);
        mainPage.openMainPage()
                .goToAuthPage();
        authPage.fillEmailField()
                .fillPasswordField()
                .clickLogInButton();
        mainPage.isAtMainPage();
        headerMenu.goToPersonalArea();
        personalAreaPage.isAtPersonalAreaPage()
                .goToAboutYourselfTab();
        AboutYourselfModel currentData = aboutYourselfTab.getFormData();
        //Assert
        assertThat(expData).isEqualToComparingFieldByField(currentData);
    }

    @BeforeEach
    private void setUp(TestInfo testInfo) {
        driver = WebDriverFactory.createDriver(BrowserType.chrome);
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @AfterEach
    private void setDown() {
        logger.info("=====тест завершился=====");
        if (driver != null) {
            driver.quit();
        }
    }

    private void clearUpCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
        logger.info("=====ПеченькО съедено :(( =====");
    }
}
