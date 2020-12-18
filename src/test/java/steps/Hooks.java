package steps;

import com.google.inject.Inject;
import cucumber.api.java.After;
import utils.Injector;

public class Hooks {

    @Inject
    private Injector injector;

    @After
    public void afterScenario() {
        if (injector.driver != null) {
            injector.driver.quit();
        }
    }
}
