package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ProjectConfig extends Config {
    @Key("url")
    String url();

    @Key("user.email")
    String userEmail();

    @Key("user.password")
    String userPassword();
}



