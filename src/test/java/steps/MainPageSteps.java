package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import pages.HeaderMenu;
import pages.MainPage;
import utils.Injector;

import java.util.List;

public class MainPageSteps{

    @Inject
    MainPage mainPage;

    @Inject
    HeaderMenu headerMenu;


    @Given("User opens Otus main page")
    public void userOpensOtusMainPage() {
        mainPage.openMainPage();
    }

    @When("User opens Auth page")
    public void userOpensAuthPage() {
        mainPage.goToAuthPage();
    }

    @And("User is on Otus main page")
    public void userIsOnOtusMainPage() {
        mainPage.isAtMainPage();
    }

    @And("User is on Otus main page - authorized")
    public void userIsOnOtusMainPageAuth() {
        mainPage.isAtMainPageAuth();
    }


    @Then("User checks header menu items")
    public void userChecksHeaderMenuItems(DataTable dataTable) {
        List<String> menuItemsFromFeature = dataTable.asList();
        List<String> menuItemsFromTest = mainPage.collectHeaderMenuItems();
        Assertions.assertLinesMatch(menuItemsFromFeature, menuItemsFromTest);
    }

    @Then("User checks subtitles are present")
    public void userChecksSubtitlesArePresent(DataTable dataTable) {
        List<String> menuItemsFromFeature = dataTable.asList();
        List<String> menuItemsFromTest = mainPage.collectPageSubTitlesItems();
        Assertions.assertLinesMatch(menuItemsFromFeature, menuItemsFromTest);
    }

    @And("User goes to Personal Area")
    public void userGoesToPersonalArea() {
        headerMenu.goToPersonalArea();
    }
}
