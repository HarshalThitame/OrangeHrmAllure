package testCases.pim;

import base.BaseTest;
import config.ConfigReader;
import factory.DriverFactory;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.pim.AddEmployeePage;
import pageObjects.pim.PIMPage;
import utils.RetryAnalyzer;

public class AddEmployeeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AddEmployeeTest.class);

    @Test(
            description = "Verify all form fields are visible on Add Employee page",
            priority = 2,
            groups = {"Smoke", "Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add Employee form should show all required UI elements")
    public void verifyAllFieldsAreVisibleOnAddEmployeePage() {

        logger.info("===== Starting test: verifyAllFieldsAreVisibleOnAddEmployeePage =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page (PIM > Add Employee)
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Verify all static fields
        Assert.assertTrue(addEmployeePage.isFirstNameVisible(), "First Name field not visible");
        Assert.assertTrue(addEmployeePage.isMiddleNameVisible(), "Middle Name field not visible");
        Assert.assertTrue(addEmployeePage.isLastNameVisible(), "Last Name field not visible");
        Assert.assertTrue(addEmployeePage.isEmployeeIdVisible(), "Employee ID field not visible");
        Assert.assertTrue(addEmployeePage.isSaveButtonVisible(), "Save button not visible");
        Assert.assertTrue(addEmployeePage.isCancelButtonVisible(), "Cancel button not visible");

        // Step 4: Toggle "Create Login Details"
        addEmployeePage.toggleCreateLoginDetails();

        // Step 5: Verify login fields appear
        Assert.assertTrue(addEmployeePage.isUsernameVisible(), "Username field not visible after toggle");
        Assert.assertTrue(addEmployeePage.isPasswordVisible(), "Password field not visible after toggle");
        Assert.assertTrue(addEmployeePage.isConfirmPasswordVisible(), "Confirm Password field not visible after toggle");
        Assert.assertTrue(addEmployeePage.isStatusEnabledRadioVisible(), "Status 'Enabled' radio not visible");
        Assert.assertTrue(addEmployeePage.isStatusDisabledRadioVisible(), "Status 'Disabled' radio not visible");

        logger.info("All form fields including login fields are visible as expected.");
        logger.info("===== Finished test: verifyAllFieldsAreVisibleOnAddEmployeePage =====");
    }

    @Test(
            description = "Add Employee with minimum required fields only",
            priority = 3,
            groups = {"Sanity", "Smoke", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Should be able to add an employee with only required fields")
    public void addEmployeeWithMinimumValidData() {

        logger.info("===== Starting test: addEmployeeWithMinimumValidData =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Fill only required fields
        String firstName = "Test";
        String lastName = "User";

        logger.info("Filling minimum required fields...");
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);  // Optional but included for clarity

        // Step 4: Click Save
        logger.info("Clicking Save button...");
        addEmployeePage.clickSubmitButton();

        // Step 5: Assert successful redirection or confirmation
        logger.info("Verifying if redirected to employee details or success indicator...");
        String successMessage = addEmployeePage.getSuccessToastMessage();
        Assert.assertTrue(successMessage.contains("Success"), "Employee creation failed or confirmation page not displayed.");

        logger.info("Employee added successfully with minimum data.");
        logger.info("===== Finished test: addEmployeeWithMinimumValidData =====");
    }

    @Test(
            description = "Verify Create Login Details toggle shows/hides login fields correctly",
            priority = 4,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Create Login Details toggle should show/hide login-related form fields dynamically")
    public void verifyCreateLoginDetailsToggleBehavior() {

        logger.info("===== Starting test: verifyCreateLoginDetailsToggleBehavior =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Enable the "Create Login Details" toggle
        logger.info("Toggling 'Create Login Details' ON...");
        addEmployeePage.toggleCreateLoginDetails(); // Assumes toggle is OFF by default

        // Step 4: Verify login-related fields become visible
        Assert.assertTrue(addEmployeePage.isUsernameVisible(), "Username field should be visible after enabling toggle");
        Assert.assertTrue(addEmployeePage.isPasswordVisible(), "Password field should be visible after enabling toggle");
        Assert.assertTrue(addEmployeePage.isConfirmPasswordVisible(), "Confirm Password field should be visible");
        Assert.assertTrue(addEmployeePage.isStatusEnabledRadioVisible(), "Status 'Enabled' radio should be visible");
        Assert.assertTrue(addEmployeePage.isStatusDisabledRadioVisible(), "Status 'Disabled' radio should be visible");

        logger.info("Login fields successfully displayed after enabling the toggle.");

        // Step 5: Disable the toggle again
        logger.info("Toggling 'Create Login Details' OFF...");
        addEmployeePage.toggleCreateLoginDetails();

        // Step 6: Verify login-related fields are hidden
        Assert.assertFalse(addEmployeePage.isUsernameVisible(), "Username field should be hidden after disabling toggle");
        Assert.assertFalse(addEmployeePage.isPasswordVisible(), "Password field should be hidden after disabling toggle");
        Assert.assertFalse(addEmployeePage.isConfirmPasswordVisible(), "Confirm Password field should be hidden");
        Assert.assertFalse(addEmployeePage.isStatusEnabledRadioVisible(), "Status 'Enabled' radio should be hidden");
        Assert.assertFalse(addEmployeePage.isStatusDisabledRadioVisible(), "Status 'Disabled' radio should be hidden");

        logger.info("Login fields successfully hidden after disabling the toggle.");
        logger.info("===== Finished test: verifyCreateLoginDetailsToggleBehavior =====");
    }

    @Test(
            description = "Verify validation messages when login details are enabled but fields are left empty",
            priority = 5,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("User should see validation if login fields are empty after enabling login details toggle")
    public void verifyLoginFieldValidationWhenLeftBlank() {

        logger.info("===== Starting test: verifyLoginFieldValidationWhenLeftBlank =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Fill First Name (mandatory field)
        String firstName = "Validation";
        addEmployeePage.enterFirstName(firstName);

        // Step 4: Enable Login Details but leave username/password fields blank
        logger.info("Enabling 'Create Login Details' toggle...");
        addEmployeePage.toggleCreateLoginDetails();

        // Step 5: Attempt to Save without filling login fields
        logger.info("Trying to save with empty login fields...");
        addEmployeePage.clickSubmitButton();

        // Step 6: Validate error messages for login fields
        logger.info("Checking for validation messages on login fields...");
        String usernameError = addEmployeePage.getUsernameRequiredMessage();
        String passwordError = addEmployeePage.getPasswordRequiredMessage();
        String confirmPasswordError = addEmployeePage.getConfirmPasswordNotMatchedMessage();

        Assert.assertTrue(usernameError.contains("Required") || usernameError.contains("empty"),
                "Username validation message missing");
        Assert.assertTrue(passwordError.contains("Required") || passwordError.contains("empty"),
                "Password validation message missing");
        Assert.assertTrue(confirmPasswordError.contains("Required") || confirmPasswordError.contains("Passwords do not match"),
                "Confirm Password validation message missing");

        logger.info("All validation messages for empty login fields verified.");
        logger.info("===== Finished test: verifyLoginFieldValidationWhenLeftBlank =====");
    }

    @Test(
            description = "Validate mismatch in Password and Confirm Password fields while adding employee",
            priority = 6,
            groups = {"Regression", "Sanity"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Mismatch in password fields should show validation error")
    public void validatePasswordAndConfirmPasswordMismatch() {

        logger.info("===== Starting test: validatePasswordAndConfirmPasswordMismatch =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Fill required fields
        logger.info("Filling First Name, Last Name, and enabling Login Details...");
        addEmployeePage.enterFirstName("Mismatch");
        addEmployeePage.enterLastName("Test");
        addEmployeePage.toggleCreateLoginDetails();

        // Step 4: Fill mismatching passwords
        addEmployeePage.enterUsername("mismatch.user");
        addEmployeePage.enterPassword("Test@1234");
        addEmployeePage.enterConfirmPassword("Mismatch@1234"); // mismatched confirm password
        addEmployeePage.selectAccountStatus("Enabled");

        // Step 5: Click Save
        logger.info("Clicking Save with mismatched passwords...");
        addEmployeePage.clickSubmitButton();

        // Step 6: Validate error message
        logger.info("Validating password mismatch error...");
        String errorMessage = addEmployeePage.getConfirmPasswordNotMatchedMessage();

        Assert.assertTrue(errorMessage.contains("Passwords do not match") || errorMessage.contains("mismatch"),
                "Expected password mismatch validation message not displayed. Message: " + errorMessage);

        logger.info("Validation for password mismatch passed successfully.");
        logger.info("===== Finished test: validatePasswordAndConfirmPasswordMismatch =====");
    }

    @Test(
            description = "Verify system does not allow duplicate Employee ID",
            priority = 9,
            groups = {"Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Should not allow creation of employee with duplicate Employee ID")
    public void verifyDuplicateEmployeeIdNotAllowed() {

        logger.info("===== Starting test: verifyDuplicateEmployeeIdNotAllowed =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Get existing Employee ID (auto-generated)
        String existingEmployeeId = ConfigReader.get("existingEmployeeId");

        // Step 4: Fill in new employee data with same Employee ID
        logger.info("Using existing Employee ID: " + existingEmployeeId);
        addEmployeePage.enterFirstName("Duplicate");
        addEmployeePage.enterLastName("Tester");

        // Step 5: Refresh page to simulate new form and enter duplicate ID
        dashboardPage.clickOnPimMenu();
        pimPage.clickAddEmployeeButton();
        addEmployeePage = pimPage.goToAddEmployeePage();

        addEmployeePage.enterFirstName("Another");
        addEmployeePage.enterLastName("Duplicate");
        addEmployeePage.enterEmployeeId(existingEmployeeId);

        logger.info("Attempting to save with duplicate Employee ID...");
        addEmployeePage.clickSubmitButton();

        // Step 6: Verify validation/error message for duplicate ID
        String errorMsg = addEmployeePage.getEidAlreadyExistMessage();
        logger.info("Validation message: " + errorMsg);

        Assert.assertTrue(errorMsg.toLowerCase().contains("already exists") || errorMsg.toLowerCase().contains("duplicate"),
                "Expected error for duplicate employee ID not shown. Message: " + errorMsg);

        logger.info("Duplicate Employee ID validation verified successfully.");
        logger.info("===== Finished test: verifyDuplicateEmployeeIdNotAllowed =====");
    }

    @Test(
            description = "Verify system does not allow duplicate usernames during employee creation",
            priority = 10,
            groups = {"Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("System should enforce unique usernames for login details")
    public void verifyDuplicateUsernameNotAllowed() {

        logger.info("===== Starting test: verifyDuplicateUsernameNotAllowed =====");

        // Step 1: Login to the system
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        // Step 2: Navigate to Add Employee page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickAddEmployeeButton();

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();

        // Step 3: Fill mandatory fields
        addEmployeePage.enterFirstName("Duplicate");
        addEmployeePage.enterLastName("User");

        // Step 4: Enable Login Details
        addEmployeePage.toggleCreateLoginDetails();

        // Step 5: Use an already existing username
        String duplicateUsername = ConfigReader.get("duplicateUsername");
        addEmployeePage.enterUsername(duplicateUsername);
        addEmployeePage.enterPassword("Pass@123");
        addEmployeePage.enterConfirmPassword("Pass@123");

        // Step 6: Click Save
        logger.info("Attempting to create employee with existing username...");
        addEmployeePage.clickSubmitButton();

        // Step 7: Validate error message for duplicate username
        String errorMsg = addEmployeePage.getUsernameAlreadyExistMessage();
        Assert.assertTrue(
                errorMsg.toLowerCase().contains("already exists") || errorMsg.toLowerCase().contains("duplicate"),
                "Expected duplicate username validation message not displayed. Found: " + errorMsg
        );

        logger.info("Duplicate username validation is working as expected.");
        logger.info("===== Finished test: verifyDuplicateUsernameNotAllowed =====");
    }

}
