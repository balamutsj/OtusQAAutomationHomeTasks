import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTest{

    @Test
    @DisplayName("User is on Otus Main page")
    void isAtOtusPage() {
        ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        String currentPageTitle = driver.getTitle();

        assert currentPageTitle.contains(expectedPageTitle);
    }
}
