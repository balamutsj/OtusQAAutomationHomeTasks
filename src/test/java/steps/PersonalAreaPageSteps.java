package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.When;
import pages.PersonalAreaPage;

public class PersonalAreaPageSteps {

    @Inject
    PersonalAreaPage personalAreaPage;

    @When("User is at Personal Area")
    public void userIsAtPersonalArea() {
        personalAreaPage.isAtPersonalAreaPage();
    }

    @When("User goes to Personal Info tab")
    public void userGoesToPersonalInfoTab() {
        personalAreaPage.goToAboutYourselfTab();
    }
}
