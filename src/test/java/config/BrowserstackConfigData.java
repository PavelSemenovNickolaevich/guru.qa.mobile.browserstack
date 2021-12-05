package config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${host}.properties"
})
public interface BrowserstackConfigData extends Config {

    String user();
    String key();
    String hostName();
    String device();
    String osVersion();
    String app();
}
