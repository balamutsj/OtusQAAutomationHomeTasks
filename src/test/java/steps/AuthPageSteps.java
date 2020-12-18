package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pages.AuthPage;
import pages.MainPage;
import utils.CommonData;
import utils.Injector;

import java.util.List;
import java.util.Map;

public class AuthPageSteps{

    @Inject
    AuthPage authPage;

    @Inject
    MainPage mainPage;

    @And("User type username")
    public void userTypeUsername() {
        authPage.fillEmailField();
    }

    @And("User type password")
    public void userTypePassword() {
        authPage.fillPasswordField();
    }

    @And("User clicks Login button")
    public void userClicksLoginButton() {
        authPage.clickLogInButton();
    }

    @When("User type username with value {string}")
    public void userTypeUsernameWithValue(String userName) {
        authPage.fillEmailField(userName);
    }

    @When("User type password with value {string}")
    public void userTypePasswordWithValue(String password) {
        authPage.fillPasswordField(password);
    }

    @Then("User checks login error msg")
    public void userChecksErrorMsgText() {
        Assert.assertEquals("Error message on login form isn't correct", CommonData.loginErrorMsgText, authPage.getErrorMsgText());
    }

    @When("User enters credentials: {word} and {word}")
    public void userEntersCredentialsUsernameAndPassword(String username, String password) {
        authPage.fillCredentials(username, password);
    }

    @And("User clicks Forgot pass")
    public void userClicksForgotPass() {
        authPage.clickForgotPassLink();
    }

    @When("User logs in as student")
    public void userLogsInAsStudent() {
        mainPage.openMainPage()
                .isAtMainPage()
                .goToAuthPage();
        userTypeUsername();
        userTypePassword();
        userClicksLoginButton();
        mainPage.isAtMainPageAuth();
    }
}
