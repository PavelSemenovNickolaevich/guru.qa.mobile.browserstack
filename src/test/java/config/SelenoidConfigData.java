package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${host}.properties"
})
public interface SelenoidConfigData extends Config {

    String user();
    String password();
    String platformName();
    String deviceName();
    String version();
    String locale();
    String language();
    String appPackage();
    String appActivity();
}
