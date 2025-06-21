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
        prop = ConfigReader.initProperties();
        String browser = prop.getProperty("browser").trim();
        String env = prop.getProperty("env").trim();
        DriverFactory.initDriver(browser, env);
        driver = DriverFactory.getDriver();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
