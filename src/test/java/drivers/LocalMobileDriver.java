package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.LocalConfigData;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalMobileDriver implements WebDriverProvider {

    static LocalConfigData localConfigData = ConfigFactory.create(LocalConfigData.class);

    static String url = localConfigData.url();
    static String platformName = localConfigData.platformName();
    static String deviceName = localConfigData.deviceName();
    static String version = localConfigData.version();
    static String locale = localConfigData.locale();
    static String language = localConfigData.language();
    static String appPackage = localConfigData.appPackage();
    static String appActivity = localConfigData.appActivity();

    public static URL getAppiumServerUrl() {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("platformName", platformName);
//        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("version", version);
        desiredCapabilities.setCapability("locale", locale);
        desiredCapabilities.setCapability("language", language);
        desiredCapabilities.setCapability("appPackage", appPackage);
        desiredCapabilities.setCapability("appActivity", appActivity);
        desiredCapabilities.setCapability("app", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));


        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}