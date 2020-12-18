package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.When;
import pages.AboutYourselfTab;

public class AboutYourselfTabSteps {

    @Inject
    AboutYourselfTab aboutYourselfTab;

    @When("User is at Personal Info tab")
    public void userIsAtPersonalInfoTab() {
        aboutYourselfTab.isAtAboutYourselfTab();
    }

    @When("User check name is editable")
    public void userChangesName() {
        aboutYourselfTab.fillFirstNameField();
    }

    @When("User check lastname is editable")
    public void userChangesLastName() {
        aboutYourselfTab.fillLastNameField();
    }

    @When("User check blogName is editable")
    public void userChangesBlogName() {
        aboutYourselfTab.fillBlogNameLatinField();
    }
}
