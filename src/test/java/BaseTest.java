import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseTest {

    private Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("=====" + testInfo.getDisplayName() + " тест=====");
    }

    @AfterEach
    public void setDown() {
        logger.info("=====тест завершился=====");
    }
}
