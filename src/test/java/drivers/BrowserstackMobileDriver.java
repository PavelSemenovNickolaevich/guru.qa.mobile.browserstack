
package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigData;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static ConfigData config = ConfigFactory.create(ConfigData.class);

    static String user = config.user();
    static String key = config.key();
    private static String browserstackUrl = config.browserstackUrl();
    private static String device = config.device();
    private static String osVersion = config.osVersion();
    private String app = config.app();

    public static URL getBrowserstackUrl() {
        try {
            return new URL(browserstackUrl);
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