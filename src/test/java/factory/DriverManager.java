package factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    public static final String hubURL = "http://localhost:4444/wd/hub";

    public static WebDriver createDriver(String browser, String env) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                yield env.equalsIgnoreCase("remote") ? createRemoteDriver(options) : new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                yield env.equalsIgnoreCase("remote") ? createRemoteDriver(options) : new FirefoxDriver(options);
            }
            default ->
                    throw new IllegalArgumentException("Unsupported browser: " + browser + " Please choose one of the available browsers");
        };
    }

    public static RemoteWebDriver createRemoteDriver(Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(hubURL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid hub URL: " + hubURL);
        }
    }
}
