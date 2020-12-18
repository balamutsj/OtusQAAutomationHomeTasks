package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.RestorePassModal;
import utils.CommonData;

public class RestorePassSteps {

    @Inject
    RestorePassModal restorePassModal;

    @And("User is on Restore Pass modal")
    public void userTypeUsername() {
        restorePassModal.isAtRestorePassModal();
    }

    @And("User types email: {word}")
    public void userTypesEmail(String email) {
        restorePassModal.fillEmailField(email);
    }

    @And("User clicks Restore button")
    public void userClicksRestoreButton() {
        restorePassModal.clickRestorePass();
    }

    @Then("User checks forgot pass error msg")
    public void userChecksErrorMsgText() {
        Assert.assertEquals("Error message on login form isn't correct",
                CommonData.forgotPassErrorMsgText, restorePassModal.getErrorMsgText());
    }

    @Then("Assert email sent")
    public void assertEmailIsSent() {
        restorePassModal.assertEmailSent();
    }
}
