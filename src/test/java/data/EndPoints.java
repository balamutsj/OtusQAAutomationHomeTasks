package data;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class EndPoints {

    private static ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    public static final String basePath = cfg.baseUrl();

    public static final String createUserPath = "/user";
    public static final String getUserPath = "/user/{username}";
}
