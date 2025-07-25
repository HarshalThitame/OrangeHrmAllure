package testCases;

import base.BaseTest;
import config.ConfigReader;
import factory.DriverFactory;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.TestDataProvider;

@SuppressWarnings("ALL")
@Epic("Authentication")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test(description = "Login with valid credentials", priority = 0, groups = {"Sanity", "Smoke", "Regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid user login successfully")
    public void loginWithValidCredentials() {

        Allure.label("browser", System.getProperty("browser"));
        Allure.label("env", System.getProperty("env"));

        logger.info("===== Starting test: loginWithValidCredentials =====");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        logger.debug("Logging in with username: {}", username);

        loginPage.login(username, password);

        logger.info("Login submitted successfully");

        DashboardPage dashBoardPage = loginPage.navigateToDashBoard();
        String validationText = dashBoardPage.getValidationText();

        logger.debug("Validation text on dashboard: {}", validationText);

        try {
            Assert.assertEquals(validationText, "Dashboard");
            logger.info("Test Passed: User is successfully logged in and landed on Dashboard");
        } catch (AssertionError e) {
            logger.error("Test Failed: Expected Dashboard text not found", e);
            throw e;
        }

        logger.info("===== Finished test: loginWithValidCredentials =====");
    }

    @Test(
            description = "Login with empty credentials",
            priority = 2,
            groups = {"Sanity", "Smoke", "Regression"}
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Show error on required text field")
    public void loginWithEmptyCredentials() {
        Allure.label("browser", System.getProperty("browser"));
        Allure.label("env", System.getProperty("env"));
        logger.info("===== Starting test: loginWithEmptyCredentials =====");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        logger.info("Attempting to login with empty username and password");
        loginPage.login("", "");

        logger.info("Login submitted");
        logger.info("Fetching 'Required' validation messages for username and password fields");
        String requiredMessageUsername = loginPage.getRequiredMessageForUsername();
        String requiredMessagePassword = loginPage.getRequiredMessageForPassword();

        logger.debug("Username required message: {}", requiredMessageUsername);
        logger.debug("Password required message: {}", requiredMessagePassword);

        try {
            Assert.assertEquals(requiredMessageUsername.trim(), "Required", "Username required message mismatch");
            Assert.assertEquals(requiredMessagePassword.trim(), "Required", "Password required message mismatch");
            logger.info("Validation messages displayed correctly for both username and password");
        } catch (AssertionError e) {
            logger.error("Validation failed for required fields", e);
            throw e;
        }

        logger.info("===== Finished test: loginWithEmptyCredentials =====");
    }

    @Test(
            description = "Login with invalid credentials using data provider",
            dataProvider = "invalidLoginData",
            dataProviderClass = TestDataProvider.class,
            priority = 2,
            groups = {"Sanity", "Regression", "Negative"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login should show proper error messages for invalid credentials")
    public void loginWithInvalidData(String username, String password, String expectedUsernameError, String expectedPasswordError) {
        Allure.label("browser", System.getProperty("browser"));
        Allure.label("env", System.getProperty("env"));
        logger.info("===== Starting test: loginWithInvalidData =====");
        logger.debug("Testing with username: '{}' and password: '{}'", username, password);
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(username, password);

        logger.info("Fetching error messages");
        if (username == "" && password == "") {
            logger.info("Testing with empty username and password");
            loginPage.login(username, password);
            logger.info("Login submitted");
            logger.info("Fetching 'Required' validation messages for username and password");
            String requiredMessageUsername = loginPage.getRequiredMessageForUsername();
            String requiredMessagePassword = loginPage.getRequiredMessageForPassword();
            logger.debug("Username required message: {}", requiredMessageUsername);
            logger.debug("Password required message: {}", requiredMessagePassword);
            Assert.assertEquals(requiredMessageUsername.trim(), expectedUsernameError, "Username required message mismatch");
            Assert.assertEquals(requiredMessagePassword.trim(), expectedPasswordError, "Password required message mismatch");
            logger.info("Validation messages displayed correctly for both username and password");
        } else if (username == "" && password != "") {
            logger.info("Testing with empty username");
            loginPage.login(username, password);
            logger.info("Login submitted");
            logger.info("Fetching 'Required' validation messages for username");
            String requiredMessageUsername = loginPage.getRequiredMessageForUsername();
            logger.debug("Username required message: {}", requiredMessageUsername);
            Assert.assertEquals(requiredMessageUsername.trim(), expectedUsernameError, "Username required message mismatch");
            logger.info("Validation messages displayed correctly for username");
        } else if (username != "" && password == "") {
            logger.info("Testing with empty password");
            loginPage.login(username, password);
            logger.info("Login submitted");
            logger.info("Fetching 'Required' validation messages for password");
            String requiredMessagePassword = loginPage.getRequiredMessageForPassword();
            logger.debug("Password required message: {}", requiredMessagePassword);
            Assert.assertEquals(requiredMessagePassword.trim(), expectedPasswordError, "Password required message mismatch");
            logger.info("Validation messages displayed correctly for password");
        } else {
            logger.info("Testing with invalid username and password");
            loginPage.login(username, password);
            logger.info("Login submitted");
            logger.info("Fetching 'Invalid Credential' validation messages");
            String invalidCredentialMessage = loginPage.getInvalidCredentialMessage();
            Assert.assertEquals(invalidCredentialMessage, "Invalid credentials", "Invalid credentials message mismatch");
            logger.info("Validation messages displayed correctly for username and password");
        }
        logger.info("===== Finished test: loginWithInvalidData =====");
    }
}
