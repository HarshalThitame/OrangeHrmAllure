package testCases.pim.editEmployeeDetails;

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
import pageObjects.pim.EmployeeListPage;
import pageObjects.pim.PIMPage;
import pageObjects.pim.editEmployeeDetails.EditContactDetailsPage;
import pageObjects.pim.editEmployeeDetails.EditEmployeePage;
import utils.RetryAnalyzer;

@Epic("PIM Module")
@Feature("Employee Contact Details")
public class EditContactDetailsTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(EditContactDetailsTest.class);

    private EditContactDetailsPage navigateToEditEmployeePage() {
        logger.info("Starting test: navigateToEditEmployeePage");
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        loginPage.login(username, password);

        logger.info("Login Successful");
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();
        employeeListPage.enterEmployeeName(ConfigReader.get("emp.name"));
        employeeListPage.clickSearchButton();
        employeeListPage.clickEditButton();

        EditEmployeePage editEmployeePage = employeeListPage.goToEditEmployeePage();
        editEmployeePage.clickContactDetailsTab();
        logger.info("Reached Contact details Component section.");

        return employeeListPage.goToEditContactDetailsPage();
    }

    @Test(
            description = "TC_PIM_CD_01 – Verify All Contact Details Fields Are Visible",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("All Contact Details fields should be visible")
    public void verifyAllContactDetailsFieldsVisible() {

        logger.info("===== Starting test: verifyAllContactDetailsFieldsVisible =====");

        EditContactDetailsPage contactDetailsPage = navigateToEditEmployeePage();

        Assert.assertTrue(contactDetailsPage.isStreet1FieldVisible(), "Street 1 not visible");
        Assert.assertTrue(contactDetailsPage.isStreet2FieldVisible(), "Street 2 not visible");
        Assert.assertTrue(contactDetailsPage.isCityFieldVisible(), "City field not visible");
        Assert.assertTrue(contactDetailsPage.isStateFieldVisible(), "State/Province field not visible");
        Assert.assertTrue(contactDetailsPage.isZipCodeFieldVisible(), "Zip Code field not visible");
        Assert.assertTrue(contactDetailsPage.isCountryDropdownVisible(), "Country dropdown not visible");

        Assert.assertTrue(contactDetailsPage.isHomePhoneFieldVisible(), "Home Phone field not visible");
        Assert.assertTrue(contactDetailsPage.isMobileFieldVisible(), "Mobile field not visible");
        Assert.assertTrue(contactDetailsPage.isWorkPhoneFieldVisible(), "Work Phone field not visible");

        Assert.assertTrue(contactDetailsPage.isWorkEmailFieldVisible(), "Work Email field not visible");
        Assert.assertTrue(contactDetailsPage.isOtherEmailFieldVisible(), "Other Email field not visible");

        Assert.assertTrue(contactDetailsPage.isSaveButtonVisible(), "Save button not visible");

        logger.info("All Contact Details fields are visible as expected.");
        logger.info("===== Finished test: verifyAllContactDetailsFieldsVisible =====");
    }


    @Test(
            description = "TC_PIM_CD_03 – Validate Successful Save With Valid Inputs",
            groups = {"Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to save valid contact details")
    public void validateSuccessfulSaveWithValidInputs() {

        logger.info("===== Starting test: validateSuccessfulSaveWithValidInputs =====");

        EditContactDetailsPage contactDetailsPage = navigateToEditEmployeePage();

        // Step 3: Enter valid contact details
        contactDetailsPage.enterStreet1("123 Test Street");
        contactDetailsPage.enterStreet2("Unit 45");
        contactDetailsPage.enterCity("Pune");
        contactDetailsPage.enterState("Maharashtra");
        contactDetailsPage.enterZipCode("411001");
        contactDetailsPage.selectCountry("India");

        contactDetailsPage.enterHomePhone("0201234567");
        contactDetailsPage.enterMobile("9876543210");
        contactDetailsPage.enterWorkPhone("0207654321");

        contactDetailsPage.enterWorkEmail("employee@example.com");
        contactDetailsPage.enterOtherEmail("employee.alt@example.com");

        // Step 4: Click Save
        contactDetailsPage.clickSaveButton();
        String toast = contactDetailsPage.getSuccessToastMessage();
        Assert.assertTrue(toast.toLowerCase().contains("success"), "Toast message did not indicate success");

        logger.info("Contact details saved successfully.");
        logger.info("===== Finished test: validateSuccessfulSaveWithValidInputs =====");
    }

    @Test(
            description = "Validate error message is shown for invalid email format in Contact Details",
            priority = 4,
            groups = {"Regression", "Sanity"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Contact Details: Validate invalid email format is rejected")
    public void validateInvalidEmailFormat() {

        logger.info("===== Starting test: validateInvalidEmailFormat =====");
        EditContactDetailsPage contactDetailsPage = navigateToEditEmployeePage();
        //Enter invalid email formats
        contactDetailsPage.enterWorkEmail("invalidEmail.com");       // Missing '@'
        contactDetailsPage.enterOtherEmail("user@wrong@domain");     // Double '@'

        //  Click Save
        contactDetailsPage.clickSaveButton();

        String workEmailErrorMessage = contactDetailsPage.getWorkEmailErrorMessage();
        String otherEmailErrorMessage = contactDetailsPage.getOtherEmailErrorMessage();

        Assert.assertTrue(workEmailErrorMessage.contains("Expected format: admin@example.com"), "Work Email error message did not match");
        Assert.assertTrue(otherEmailErrorMessage.contains("Expected format: admin@example.com"), "Other Email error message did not match");

        logger.info("Validation for invalid email format verified.");
        logger.info("===== Finished test: validateInvalidEmailFormat =====");

    }

    @Test(
            description = "Validate phone number fields only accept numeric input",
            priority = 5,
            groups = {"Regression", "Sanity"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Contact Details: Phone fields should reject non-numeric input")
    public void validatePhoneNumberFieldAcceptsOnlyNumbers() {

        logger.info("===== Starting test: validatePhoneNumberFieldAcceptsOnlyNumbers =====");
        EditContactDetailsPage contactDetailsPage = navigateToEditEmployeePage();

        contactDetailsPage.enterHomePhone("ABC123");    // Invalid
        contactDetailsPage.enterWorkPhone("!@#123");   // Invalid
        contactDetailsPage.enterMobile("123XYZ");  // Invalid

        contactDetailsPage.clickSaveButton();

        Assert.assertTrue(contactDetailsPage.getHomeTelephoneErrorMessage().contains("Allows numbers and only + - / ( )"), "Home Telephone error message did not match");
        Assert.assertTrue(contactDetailsPage.getMobileTelephoneErrorMessage().contains("Allows numbers and only + - / ( )"), "Mobile Telephone error message did not match");
        Assert.assertTrue(contactDetailsPage.getWorkTelephoneErrorMessage().contains("Allows numbers and only + - / ( )"), "Work Telephone error message did not match");

        logger.info("Phone number fields correctly rejected non-numeric input.");
        logger.info("===== Finished test: validatePhoneNumberFieldAcceptsOnlyNumbers =====");
    }

    @Test(
            description = "TC_PIM_CD_10 – Validate max character limits in contact details fields",
            priority = 10,
            groups = {"Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Field character limits should be enforced on Contact Details page")
    public void validateFieldCharacterLimits() {

        logger.info("===== Starting test: validateFieldCharacterLimits =====");
        EditContactDetailsPage contactDetailsPage = navigateToEditEmployeePage();

        // Enter values beyond max allowed characters
        contactDetailsPage.enterStreet1("a".repeat(80));           // Max 70
        contactDetailsPage.enterStreet2("b".repeat(80));           // Max 70
        contactDetailsPage.enterCity("c".repeat(80));              // Max 70
        contactDetailsPage.enterState("d".repeat(80));             // Max 70
        contactDetailsPage.enterZipCode("123456789012");           // Max 10
        contactDetailsPage.enterHomePhone("x".repeat(30));         // Max 25
        contactDetailsPage.enterMobile("5".repeat(30));       // Max 25
        contactDetailsPage.enterWorkPhone("y".repeat(30));         // Max 25
        contactDetailsPage.enterWorkEmail("w".repeat(60) + "@x.com");  // Max 50
        contactDetailsPage.enterOtherEmail("o".repeat(60) + "@y.com"); // Max 50


        Assert.assertTrue(contactDetailsPage.getStreet1ErrorMessage().contains("Should not exceed 70 characters"),
                "❌ Validation failed: Street 1 error message mismatch. Expected message to contain: 'Should not exceed 70 characters'");

        Assert.assertTrue(contactDetailsPage.getStreet2ErrorMessage().contains("Should not exceed 70 characters"),
                "❌ Validation failed: Street 2 error message mismatch. Expected message to contain: 'Should not exceed 70 characters'");

        Assert.assertTrue(contactDetailsPage.getCityErrorMessage().contains("Should not exceed 70 characters"),
                "❌ Validation failed: City error message mismatch. Expected message to contain: 'Should not exceed 70 characters'");

        Assert.assertTrue(contactDetailsPage.getStateErrorMessage().contains("Should not exceed 70 characters"),
                "❌ Validation failed: State/Province error message mismatch. Expected message to contain: 'Should not exceed 70 characters'");

        Assert.assertTrue(contactDetailsPage.getZipCodeErrorMessage().contains("Should not exceed 10 characters"),
                "❌ Validation failed: Zip/Postal Code error message mismatch. Expected message to contain: 'Should not exceed 10 characters'");

        Assert.assertTrue(contactDetailsPage.getHomeTelephoneErrorMessage().contains("Should not exceed 25 characters"),
                "❌ Validation failed: Home Telephone error message mismatch. Expected message to contain: 'Should not exceed 25 characters'");

        Assert.assertTrue(contactDetailsPage.getMobileTelephoneErrorMessage().contains("Should not exceed 25 characters"),
                "❌ Validation failed: Mobile Telephone error message mismatch. Expected message to contain: 'Should not exceed 25 characters'");

        Assert.assertTrue(contactDetailsPage.getWorkTelephoneErrorMessage().contains("Should not exceed 25 characters"),
                "❌ Validation failed: Work Telephone error message mismatch. Expected message to contain: 'Should not exceed 25 characters'");

        Assert.assertTrue(contactDetailsPage.getWorkEmailErrorMessage().contains("Should not exceed 50 characters"),
                "❌ Validation failed: Work Email error message mismatch. Expected message to contain: 'Should not exceed 50 characters'");

        Assert.assertTrue(contactDetailsPage.getOtherEmailErrorMessage().contains("Should not exceed 50 characters"),
                "❌ Validation failed: Other Email error message mismatch. Expected message to contain: 'Should not exceed 50 characters'");

        logger.info("Character limit validations appeared as expected.");

        logger.info("===== Finished test: validateFieldCharacterLimits =====");
    }
}
