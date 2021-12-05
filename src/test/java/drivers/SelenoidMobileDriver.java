package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.SelenoidConfigData;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidMobileDriver implements WebDriverProvider {

    static SelenoidConfigData selenoidConfigData = ConfigFactory.create(SelenoidConfigData.class);

    static String user = selenoidConfigData.user();
    static String password = selenoidConfigData.password();
    static String platformName = selenoidConfigData.platformName();
    static String deviceName = selenoidConfigData.deviceName();
    static String version = selenoidConfigData.version();
    static String locale = selenoidConfigData.locale();
    static String language = selenoidConfigData.language();
    static String appPackage = selenoidConfigData.appPackage();
    static String appActivity = selenoidConfigData.appActivity();

    public static URL getSelenoidUrl() {
        try {
            return new URL("https://" + user + ":" + password + "@selenoid.autotests.cloud/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("version", version);
        desiredCapabilities.setCapability("locale", locale);
        desiredCapabilities.setCapability("language", language);
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", true);
        desiredCapabilities.setCapability("appPackage", appPackage);
        desiredCapabilities.setCapability("appActivity", appActivity);
        desiredCapabilities.setCapability("app", apkUrl());

        return new AndroidDriver(getSelenoidUrl(), desiredCapabilities);
    }

    private URL apkUrl() {
        try {
            return new URL("(https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}