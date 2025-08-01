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
import pageObjects.pim.editEmployeeDetails.EditEmployeePage;
import pageObjects.pim.editEmployeeDetails.EditSalaryDetailsPage;
import utils.RetryAnalyzer;
import utils.TestDataProvider;

@Epic("PIM Module")
@Feature("Salary Component")
public class EditSalaryDetailsTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(EditSalaryDetailsTest.class);

    private EditSalaryDetailsPage navigateToEditSalaryDetailsPage() {
        logger.info("Navigating to Salary Details tab of the employee");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();

        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();
        employeeListPage.enterEmployeeName(ConfigReader.get("emp.name"));
        employeeListPage.clickSearchButton();
        employeeListPage.clickEditButton();

        EditEmployeePage editEmployeePage = employeeListPage.goToEditEmployeePage();
        editEmployeePage.clickSalaryTab();
        logger.info("Reached Salary Component section.");

        return employeeListPage.goToEditSalaryDetailsPage();
    }

    @Test(description = "Verify visibility of all Personal and Custom Fields",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Ensure all fields under Personal Details and Custom Fields are visible")
    public void verifyAllSalaryComponentFieldsAreVisible()  {
        logger.info("===== Starting test: verifyAllSalaryComponentFieldsVisible =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        salaryPage.clickDirectDepositToggle();
        Allure.step("Navigate to the Salary Component section");

        Assert.assertTrue(salaryPage.isSalaryComponentTextFieldVisible(), "Salary Component field is NOT visible");
        Allure.step("Verified 'Salary Component' text field is visible");

        Assert.assertTrue(salaryPage.isAmountTextField1Visible(), "Amount 1 field is NOT visible");
        Allure.step("Verified 'Amount 1' text field is visible");

        Assert.assertTrue(salaryPage.isCommentTextAreaVisible(), "Comment text area is NOT visible");
        Allure.step("Verified 'Comments' text area is visible");

        Assert.assertTrue(salaryPage.isPayGradeDropdownVisible(), "Pay Grade dropdown is NOT visible");
        Allure.step("Verified 'Pay Grade' dropdown is visible");

        Assert.assertTrue(salaryPage.isPayFrequencyDropdownVisible(), "Pay Frequency dropdown is NOT visible");
        Allure.step("Verified 'Pay Frequency' dropdown is visible");

        Assert.assertTrue(salaryPage.isCurrencyDropdownVisible(), "Currency dropdown is NOT visible");
        Allure.step("Verified 'Currency' dropdown is visible");

        Assert.assertTrue(salaryPage.isDirectDepositToggleVisible(), "Direct Deposit toggle is NOT visible");
        Allure.step("Verified 'Direct Deposit' toggle is visible");

        Assert.assertTrue(salaryPage.isAccountNumberTextFieldVisible(), "Account Number field is NOT visible");
        Allure.step("Verified 'Account Number' text field is visible");

        Assert.assertTrue(salaryPage.isRoutingNumberTextFieldVisible(), "Routing Number field is NOT visible");
        Allure.step("Verified 'Routing Number' text field is visible");

        Assert.assertTrue(salaryPage.isAmountTextField2Visible(), "Amount 2 field is NOT visible");
        Allure.step("Verified 'Amount 2' text field is visible");

        Assert.assertTrue(salaryPage.isAccountTypeDropdownVisible(), "Account Type dropdown is NOT visible");
        Allure.step("Verified 'Account Type' dropdown is visible");

        Assert.assertTrue(salaryPage.isCancelButtonVisible(), "Cancel button is NOT visible");
        Allure.step("Verified 'Cancel' button is visible");

        Assert.assertTrue(salaryPage.isSaveButtonVisible(), "Save button is NOT visible");
        Allure.step("Verified 'Save' button is visible");

        logger.info("All Salary Component fields are visible as expected.");
        logger.info("===== Finished test: verifyAllSalaryComponentFieldsVisible =====");
    }

    @Test(
            description = "TC_PIM_SC_02 – Validate Required Field Validation",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validate required field error messages appear on Salary Component form")
    public void validateRequiredFieldValidation() {
        logger.info("===== Starting test: validateRequiredFieldValidation =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Clicked on Add Salary Component button");

        // Intentionally do NOT enter any value

        salaryPage.clickSaveButton();
        Allure.step("Clicked Save without entering mandatory fields");

        // Assertions
        String salaryComponentError = salaryPage.getSalaryComponentErrorMessage();
        String amountError = salaryPage.getAmount1ErrorMessage();
        String currencyError = salaryPage.getCurrencyErrorMessage();

        Assert.assertTrue(salaryComponentError.contains("Required"),
                "❌ Salary Component error message not displayed correctly");
        Allure.step("Validated error for Salary Component: " + salaryComponentError);

        Assert.assertTrue(amountError.contains("Required"),
                "❌ Amount error message not displayed correctly");
        Allure.step("Validated error for Amount: " + amountError);

        Assert.assertTrue(currencyError.contains("Required"),
                "❌ Currency error message not displayed correctly");
        Allure.step("Validated error for Currency: " + currencyError);

        logger.info("All required field validations appeared as expected.");
        logger.info("===== Finished test: validateRequiredFieldValidation =====");
    }

    @Test(
            description = "TC_PIM_SC_03 – Validate Amount Field Accepts Only Numbers",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validate that the Amount field accepts only numeric input")
    public void validateAmountFieldAcceptsOnlyNumbers() {
        logger.info("===== Starting test: validateAmountFieldAcceptsOnlyNumbers =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Clicked on Add Salary Component button");

        salaryPage.enterAmount1("ABC123!@#");  // Invalid input
        Allure.step("Entered invalid characters in Amount field: ABC123!@#");

        salaryPage.clickSaveButton();
        Allure.step("Clicked Save");

        String amountError = salaryPage.getAmount1ErrorMessage();

        Assert.assertTrue(amountError.contains("Should be a number") || amountError.toLowerCase().contains("numeric"),
                "❌ Amount field did not reject invalid input properly. Expected numeric validation error.");
        Allure.step("Validated numeric-only error on Amount field: " + amountError);

        logger.info("Amount field validation for numeric input passed.");
        logger.info("===== Finished test: validateAmountFieldAcceptsOnlyNumbers =====");
    }

    @Test(
            description = "TC_PIM_SC_04 – Validate Save Functionality with Valid Data",
            groups = {"Regression", "Sanity"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Salary Component: Validate that valid salary component can be saved successfully")
    public void validateSaveFunctionalityWithValidData()  {
        logger.info("===== Starting test: validateSaveFunctionalityWithValidData =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Clicked on Add Salary Component button");

        salaryPage.enterSalaryComponent("Performance Bonus");
        Allure.step("Entered Salary Component: Performance Bonus");

        salaryPage.selectPayGrade("Grade 2"); // You can use any available grade
        Allure.step("Selected Pay Grade: Grade 2");

        salaryPage.selectPayFrequency("Monthly");
        Allure.step("Selected Pay Frequency: Monthly");

        salaryPage.selectCurrency("United States Dollar");
        Allure.step("Selected Currency: USD");

        salaryPage.enterAmount1("50000");
        Allure.step("Entered Amount: 50000");

        salaryPage.enterComment("Quarterly Performance Bonus");
        Allure.step("Entered optional Comment");

        salaryPage.clickDirectDepositToggle();
        Allure.step("Toggled Direct Deposit to ON");

        salaryPage.enterAccountNumber("123456789");
        Allure.step("Entered Account Number");

        salaryPage.enterRoutingNumber("987654321");
        Allure.step("Entered Routing Number");

        salaryPage.enterAmount2("50000");
        Allure.step("Entered Amount 2 for Direct Deposit");

        salaryPage.selectAccountType("Savings");
        Allure.step("Selected Account Type: Savings");

        salaryPage.clickSaveButton();
        Allure.step("Clicked Save");

        // Validation: assert success toast or entry in assigned table
        String toastMessage = salaryPage.getSuccessToastMessage();
        Assert.assertTrue(toastMessage.toLowerCase().contains("success"),
                "❌ Salary Component Save Failed. Toast: " + toastMessage);

        Allure.step("Verified success message: " + toastMessage);
        logger.info("Salary component saved and verified successfully.");
        logger.info("===== Finished test: validateSaveFunctionalityWithValidData =====");
    }

    @Test(
            description = "TC_PIM_SC_06 – Validate Cancel Button Functionality",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify that the Cancel button clears the form and closes the modal or resets the state")
    public void validateCancelButtonFunctionality()  {
        logger.info("===== Starting test: validateCancelButtonFunctionality =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Clicked 'Add Salary Component'");

        // Fill partial form
        salaryPage.enterSalaryComponent("Test Allowance");
        Allure.step("Entered Salary Component");

        salaryPage.enterAmount1("15000");
        Allure.step("Entered Amount 1");

        salaryPage.enterComment("Temporary test entry");
        Allure.step("Entered Comment");

        // Click Cancel
        salaryPage.clickCancelButton();
        Allure.step("Clicked Cancel button");


        // Re-open form to check if fields are cleared
        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Re-opened 'Add Salary Component' form");

        // Validate fields are cleared
        String salaryComponentValue = salaryPage.getValueOfField("salaryComponent");
        String amount1Value = salaryPage.getValueOfField("amount1");
        String commentValue = salaryPage.getValueOfField("comment");

        Assert.assertTrue(salaryComponentValue.isEmpty(), "Salary Component field was not cleared");
        Assert.assertTrue(amount1Value.isEmpty(), "Amount 1 field was not cleared");
        Assert.assertTrue(commentValue.isEmpty(), "Comment field was not cleared");

        Allure.step("Verified that form fields are cleared after cancel");

        logger.info("Form was reset/cleared successfully after clicking Cancel.");
        logger.info("===== Finished test: validateCancelButtonFunctionality =====");
    }

    @Test(
            description = "TC_PIM_SC_07 – Validate Direct Deposit Toggle Behavior",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Toggle Direct Deposit should show/hide related banking fields")
    public void validateDirectDepositToggleBehavior() {
        logger.info("===== Starting test: validateDirectDepositToggleBehavior =====");

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();
        Allure.step("Clicked 'Add Salary Component'");

        // Step 1: Enable Direct Deposit
        salaryPage.clickDirectDepositToggle();
        Allure.step("Enabled Direct Deposit toggle");

        // Verify related fields are visible
        Assert.assertTrue(salaryPage.isAccountNumberTextFieldVisible(), "❌ Account Number field is NOT visible after enabling Direct Deposit");
        Allure.step("Verified 'Account Number' is visible");

        Assert.assertTrue(salaryPage.isRoutingNumberTextFieldVisible(), "❌ Routing Number field is NOT visible after enabling Direct Deposit");
        Allure.step("Verified 'Routing Number' is visible");

        Assert.assertTrue(salaryPage.isAmountTextField2Visible(), "❌ Amount 2 field is NOT visible after enabling Direct Deposit");
        Allure.step("Verified 'Amount 2' is visible");

        Assert.assertTrue(salaryPage.isAccountTypeDropdownVisible(), "❌ Account Type dropdown is NOT visible after enabling Direct Deposit");
        Allure.step("Verified 'Account Type' dropdown is visible");

        // Optional: Step 2 – Toggle OFF (depends on if fields are truly hidden dynamically)
        salaryPage.clickDirectDepositToggle();
        Allure.step("Disabled Direct Deposit toggle");

        Assert.assertFalse(salaryPage.isAccountNumberTextFieldVisible(), "❌ Account Number field is visible after disabling Direct Deposit");
        Allure.step("Verified 'Account Type' dropdown is visible");

        logger.info("Direct Deposit toggle behavior validated successfully.");
        logger.info("===== Finished test: validateDirectDepositToggleBehavior =====");
    }

    @Test(dataProvider = "amountBVAData",
            dataProviderClass = TestDataProvider.class,
            description = "TC_PIM_SC_08 – Validate BVA for Amount field (Min: 40000, Max: 50000)",
            groups = {"Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validate BVA on Amount field in Salary Component")
    public void validateAmountFieldBoundaryValues(String amount, boolean expectError, String expectedErrorMessage) {
        logger.info("===== Starting test with Amount: {} =====", amount);

        EditSalaryDetailsPage salaryPage = navigateToEditSalaryDetailsPage();

        salaryPage.clickAddSalaryComponentButton();

        salaryPage.enterSalaryComponent("Test Salary");
        salaryPage.selectPayGrade("Grade 2");
        salaryPage.selectPayFrequency("Monthly");
        salaryPage.selectCurrency("United States Dollar");
        salaryPage.enterAmount1(amount);
        salaryPage.clickSaveButton();

        if (expectError) {
            String actualError = salaryPage.getAmount1ErrorMessage();
            Allure.step("Expected Error: " + expectedErrorMessage);
            Allure.step("Actual Error: " + actualError);
            Assert.assertTrue(actualError.contains(expectedErrorMessage),
                    "❌ Error message mismatch for amount: " + amount);
            logger.error("Validation failed as expected for invalid amount: {}", amount);
        } else {
            // If no error expected, assert error message is not displayed
            boolean actualError = salaryPage.isAmount1ErrorMessageVisible();
            Assert.assertFalse(actualError,
                    "❌ Unexpected error shown for valid amount: " + amount);
            logger.info("No error shown for valid amount: {}", amount);
        }

        logger.info("===== Finished test with Amount: {} =====", amount);
    }
}
