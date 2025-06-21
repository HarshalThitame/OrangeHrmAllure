package factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void initDriver(String browser, String env) {
        WebDriver driver = DriverManager.createDriver(browser, env);
        tlDriver.set(driver);
        getDriver().manage().window().maximize();

    }

    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }

}
