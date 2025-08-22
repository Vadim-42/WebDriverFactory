package factory;

import exceptions.UnsupportedBrowserException;
import factory.settings.ChromeSettings;
import factory.settings.EdgeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class WebDriverFactory {

    private static final String remoteUrl = System.getProperty("remote.url", "");

    private static final String browserName = System.getProperty("browser.name", "chrome");

    private static final String browserVersion = System.getProperty("browser.version", "128.0");

    public static WebDriver create(String browser, Optional<String> mode) throws MalformedURLException {

        if(!remoteUrl.isEmpty()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
            Map<String, Object> options = new HashMap<>();
            options.put(CapabilityType.BROWSER_VERSION, browserVersion);
            options.put("enableVNC", true);
            capabilities.setCapability("selenoid:options", options);

            return new RemoteWebDriver(new URL(remoteUrl), capabilities);
        }

        switch (browser) {
            case "chrome": {
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings(String.valueOf(mode)));
            }
            case "edge": {
                return new EdgeDriver((EdgeOptions) new EdgeSettings().settings(String.valueOf(mode)));
            }
        }
        throw new UnsupportedBrowserException(browser);
    }

    public void webDriverManagerSetup(String browser) {
        switch (browser) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                return;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                return;
            }
        }
        throw new UnsupportedBrowserException(browser);
    }
}
