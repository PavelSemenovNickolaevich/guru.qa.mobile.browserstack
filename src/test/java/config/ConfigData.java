package config;

import org.aeonbits.owner.Config;


@org.aeonbits.owner.Config.Sources({"classpath:credentials.properties"})
public interface ConfigData extends org.aeonbits.owner.Config {

    String user();
    String key();
    String browserstackUrl();
    String device();
    String osVersion();
    String app();
}
