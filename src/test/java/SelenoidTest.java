import config.ProjectConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static org.assertj.core.api.Assertions.assertThat;


@Epic("The Epic from JIRA: 'user personal data'")
@Story("Here goes some story link: http://jiraticket123478.jira.com")
public class SelenoidTest extends BaseTest{

    private Logger logger = LogManager.getLogger(SelenoidTest.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Test
    @Step("Test: 'otusUserInfoTest1'")
    @Description("The first test case in this class")
    void otusUserInfoTest1() {
        MainPage mainPage = new MainPage(driver);
        AuthPage authPage = new AuthPage(driver);
        mainPage.openMainPage()
                .goToAuthPage();
        authPage.fillEmailField()
                .fillPasswordField()
                .clickLogInButton();
        mainPage.isAtMainPage();
    }

    @Test
    @Step("Test: 'otusUserInfoTest2'")
    @Description("The second test case in this class")
    void otusUserInfoTest2() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .goToAuthPage();
    }
}
