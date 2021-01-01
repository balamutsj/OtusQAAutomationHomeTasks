import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.Locale;

public class BaseTest {

    private Logger logger = LogManager.getLogger(BaseTest.class);
    protected static Faker faker = new Faker(new Locale("en-GB"));
    protected static FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @AfterEach
    public void setDown() {
        logger.info("=====тест завершился=====");
    }
}
