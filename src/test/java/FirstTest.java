import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTest {

    private ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Test
    @DisplayName("isAtOtusMainPage")
    void isAtOtusMainPage() {
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        String currentPageTitle = driver.getTitle();

        assert currentPageTitle.contains(expectedPageTitle);
    }
}
