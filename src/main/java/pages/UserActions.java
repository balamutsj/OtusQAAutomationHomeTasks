package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserActions {

    WebDriver driver;

    public UserActions (WebDriver driver) {
        this.driver = driver;
    }

    //ACTIONS
    public UserActions openPage(String url, WebElement pageLocator) {
        driver.get(url);
        waitElemIsDisplayed(pageLocator);
        return this;
    }

    public UserActions isAt(WebElement pageLocator) {
        waitElemIsDisplayed(pageLocator);
        return this;
    }

    public UserActions clickOnVisibleElement(WebElement locator) {
        waitElemIsDisplayed(locator);
        locator.click();
        return this;
    }

    public UserActions typeText(WebElement locator, String text) {
        waitElemIsDisplayed(locator);
        locator.clear();
        locator.sendKeys(text);
        return this;
    }

    public UserActions hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        waitElemIsDisplayed(element);
        action.moveToElement(element).perform();
        return this;
    }

    //WAITS

    public UserActions waitElemIsDisplayed(WebElement locator, Integer... timeout) {
        waitFor(ExpectedConditions.visibilityOf(locator),
                (timeout.length > 0 ? timeout[0] : null));
        return this;
    }

    public UserActions waitElemIsNotDisplayed(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(locator));
        return this;
    }

    public UserActions waitElemToBeClickable(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    //DATA GENERATIONS

    public String setDefaultValue(String fakerData, String... value) {
        String returnValue = null;
        if (value.length > 0) {
            returnValue = value[0];
        } else {
            returnValue = fakerData;
        }
        return returnValue;
    }
}
