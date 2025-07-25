package base;

import config.ConfigReader;
import factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;

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

    @BeforeMethod
    public void setUp() {
        // Load the configuration properties (e.g., browser type, environment, URL, etc.)
        ConfigReader.loadProperties("qa");

        // Read and trim the 'browser' value from the properties file
        String browser = ConfigReader.get("browser");
//        String browser = System.getProperty("browser");

        // Read and trim the 'env' (environment) value from the properties file (e.g., QA, Staging)
        String env = ConfigReader.get("env");
//        String env = System.getProperty("env", "local");

        Allure.label("browser", browser);
        Allure.label("environment", env);


        // Initialize the WebDriver based on the browser and environment
        DriverFactory.initDriver(browser, env);

        // Get the WebDriver instance from the DriverFactory
        driver = DriverFactory.getDriver();

        // Navigate to the application URL as specified in the properties file
        driver.get(ConfigReader.get("url"));
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }


//    public String takeScreenshot() {
//        String path = System.getProperty("user.dir") + "\\src\\reports\\screenshots\\";
//        try {
//            TakesScreenshot screenshot = ((TakesScreenshot) DriverFactory.getDriver());
//            File ssFile = screenshot.getScreenshotAs(OutputType.FILE);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
