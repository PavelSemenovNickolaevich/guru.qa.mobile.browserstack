
package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfigData;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static BrowserstackConfigData config = ConfigFactory.create(BrowserstackConfigData.class);

    static String user = config.user();
    static String key = config.key();
    static String hostName = config.hostName();
    static String device = config.device();
    static String osVersion = config.osVersion();
    private String app = config.app();


    public static URL getBrowserstackUrl() {
        try {
            return new URL(hostName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", user);
        desiredCapabilities.setCapability("browserstack.key", key);

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", app);

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("device", device);
        desiredCapabilities.setCapability("os_version", osVersion);

        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", "First Java Project");
        desiredCapabilities.setCapability("build", "Java Android");
        desiredCapabilities.setCapability("name", "first_test_browserStack");

        return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}