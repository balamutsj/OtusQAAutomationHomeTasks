import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeTask06 {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask06.class);
    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        driver = WebDriverFactory.createDriver(BrowserType.chrome);
        logger.info("Драйвер поднят");
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @Test
    @DisplayName("compareSmartphones")
    void compareSmartphones() {
        String baseUrl = cfg.url();
        String SAMSUNG = "samsung";
        String XIAOMI = "xiaomi";
        By yandexMarketMainPageLogo = By.xpath("//div[@data-apiary-widget-id='/header/logo']");
        By electronicsMainMenuItem = By.xpath("//span[text() = 'Электроника']");
        By electronicsPageHeader = By.xpath("//h1[text()='Электроника']");
        By smartPhoneSideMenuItem = By.xpath("//ul//a[text() = 'Смартфоны']");
        By manufacturerSamsungFilterItem = By.xpath("//fieldset//span[text()='Samsung']");
        By manufacturerXiaomiFilterItem = By.xpath("//fieldset//span[text()='Xiaomi']");
        By smartPhonesPageHeader = By.xpath("//h1");
        By articlePhoneTitle = By.xpath("//h3/a");
        By searchResultBlocker = By.xpath("//div[contains(@class, '_2LvbieS_AO')]");
        By byPriceFilter = By.xpath("//button[text()='по цене']");
        By addToComparePopUpCloseBtn = By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//button");
        By addToComparePopUp = By.xpath("//div[contains(text(), 'добавлен к сравнению')]");
        By addToComparePopUpCompareBtn = By.xpath("//a//span[text()='Сравнить']");
        By smartPhonesComparePageHeader = By.xpath("//div[text()='Сравнение товаров']");
        By smartPhoneCompareName = By.xpath("//div[@class='e910RKmlsj']//a");

        driver.get(baseUrl);
        waitElemIsDisplayed(yandexMarketMainPageLogo);
        logger.info("===== на главной 'Яндекс.Маркет'");
        clickOnVisibleElement(electronicsMainMenuItem);
        waitElemIsDisplayed(electronicsPageHeader);
        logger.info("===== на странице 'Электроника'");
        clickOnVisibleElement(smartPhoneSideMenuItem);
        waitElemIsDisplayed(smartPhonesPageHeader);
        logger.info("===== на странице 'Смартфоны'");
        clickOnVisibleElement(manufacturerSamsungFilterItem);
        clickOnVisibleElement(manufacturerXiaomiFilterItem);
        waitBlockerToDisAppear(searchResultBlocker);
        clickOnVisibleElement(byPriceFilter);
        waitBlockerToDisAppear(searchResultBlocker);

        HashMap<String, String> samsungData = getPhoneDataFromListItem(articlePhoneTitle, SAMSUNG);
        clickOnNotVisibleElement(customXpathLocatorAddToCompare(samsungData.get("index")));
        waitElemIsDisplayed(addToComparePopUp);
        logger.info(String.format("===== смартфон: %s", samsungData.get("phoneData")));
        assertThat(driver.findElement(addToComparePopUp).getText()).contains(samsungData.get("phoneData"));
        clickOnVisibleElement(addToComparePopUpCloseBtn);

        HashMap<String, String> xiaomiData = getPhoneDataFromListItem(articlePhoneTitle, XIAOMI);
        clickOnNotVisibleElement(customXpathLocatorAddToCompare(xiaomiData.get("index")));
        waitElemIsDisplayed(addToComparePopUp);
        logger.info(String.format("===== смартфон: %s", xiaomiData.get("phoneData")));
        assertThat(driver.findElement(addToComparePopUp).getText()).contains(xiaomiData.get("phoneData"));

        clickOnVisibleElement(addToComparePopUpCompareBtn);
        waitElemIsDisplayed(smartPhonesComparePageHeader);
        logger.info("===== на странице 'Сравнить товар'");

        waitElemIsDisplayed(smartPhoneCompareName);
        List<String> comparisonList = convertData(driver.findElements(smartPhoneCompareName));
        assertThat(comparisonList).hasSize(2)
                        .contains(samsungData.get("phoneData"), xiaomiData.get("phoneData"));
    }

    @AfterEach
    public void setDown() {
        logger.info("=====тест завершился=====");
        if (driver != null) {
            driver.quit();
        }
    }

    private void clickOnVisibleElement(By locator) {
        waitElemIsDisplayed(locator);
        driver.findElement(locator).click();
    }

    private void clickOnNotVisibleElement(By locator) {
        driver.findElement(locator).click();
    }

    private void waitBlockerToDisAppear(By locator) {
        waitElemIsDisplayed(locator);
        waitElemIsNotDisplayed(locator);
    }

    private HashMap<String, String> getPhoneDataFromListItem(By namesLocator, String smartPhoneName) {
        HashMap<String, String> map = new HashMap<>();
        List<WebElement> phoneNames = driver.findElements(namesLocator);
        for (WebElement phoneName : phoneNames) {
            if(phoneName.getText().toLowerCase().contains(smartPhoneName)) {
                Integer index = phoneNames.indexOf(phoneName) + 1;
                map.put("index", index.toString());
                map.put("phoneData", phoneName.getText());
                break;
            }
        }
        return map;
    }



    private List<String> convertData(List<WebElement> list) {
        List<String> strList = new ArrayList<>();
        for(WebElement listItem : list) {
            strList.add(listItem.getText());
        }
        return strList;
    }

    private void waitElemIsDisplayed(By locator, Integer... timeout) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
    }

    private void waitElemIsNotDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    private By customXpathLocatorAddToCompare(String index) {
        return By.xpath("(//div[@class='_1CXljk9rtd'])" + String.format("[%s]", index));
    }

}
