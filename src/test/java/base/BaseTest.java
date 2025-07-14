package base;

import config.ConfigReader;
import factory.DriverFactory;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Listeners({TestListener.class, AllureTestNg.class})
public class BaseTest {
    static {
        // Suppress Selenium JUL logs
        Logger seleniumLogger = Logger.getLogger("org.openqa.selenium");
        seleniumLogger.setLevel(Level.SEVERE); // Only show SEVERE logs

        // Optional: suppress root JUL logger
        Logger.getLogger("").setLevel(Level.SEVERE);
    }

    protected WebDriver driver;
    protected Properties prop;

    @BeforeMethod
    public void setUp() {
        // Load the configuration properties (e.g., browser type, environment, URL, etc.)
        prop = ConfigReader.initProperties();

        // Read and trim the 'browser' value from the properties file
//        String browser = prop.getProperty("browser").trim();
        String browser = System.getProperty("browser").trim();

        // Read and trim the 'env' (environment) value from the properties file (e.g., QA, Staging)
//        String env = prop.getProperty("env").trim();
        String env = System.getProperty("env").trim();

        // Initialize the WebDriver based on the browser and environment
        DriverFactory.initDriver(browser, env);

        // Get the WebDriver instance from the DriverFactory
        driver = DriverFactory.getDriver();

        // Navigate to the application URL as specified in the properties file
        driver.get(prop.getProperty("url"));
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
