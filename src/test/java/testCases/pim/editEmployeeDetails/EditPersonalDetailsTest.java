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
import pageObjects.pim.editEmployeeDetails.EditPersonalDetailPage;
import utils.RetryAnalyzer;

@Epic("PIM Module")
@Feature("Edit Personal Details Feature")
public class EditPersonalDetailsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(EditPersonalDetailsTest.class);

    private EditPersonalDetailPage navigateToEditEmployeePage() {
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

        return employeeListPage.goToEditPersonalDetailPage();
    }

    @Test(description = "Verify visibility of all Personal and Custom Fields",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Ensure all fields under Personal Details and Custom Fields are visible")
    public void verifyAllPersonalAndCustomFieldsVisible() {
        logger.info("Starting test: verifyAllPersonalAndCustomFieldsVisible");

        EditPersonalDetailPage page = navigateToEditEmployeePage();

        Assert.assertTrue(page.isEmployeeFirstNameFieldVisible(), "First Name field not visible");
        Assert.assertTrue(page.isEmployeeMiddleNameFieldVisible(), "Middle Name field not visible");
        Assert.assertTrue(page.isEmployeeLastNameFieldVisible(), "Last Name field not visible");
        Assert.assertTrue(page.isEmployeeIdFieldVisible(), "Employee ID field not visible");
        Assert.assertTrue(page.isOtherIdFieldVisible(), "Other ID field not visible");
        Assert.assertTrue(page.isLicenseNumberFieldVisible(), "License Number field not visible");
        Assert.assertTrue(page.isExpiryDateFieldVisible(), "License Expiry Date not visible");
        Assert.assertTrue(page.isNationalityDropdownVisible(), "Nationality dropdown not visible");
        Assert.assertTrue(page.isMaritalStatusDropdownVisible(), "Marital Status dropdown not visible");
        Assert.assertTrue(page.isDobDropdownVisible(), "DOB field not visible");
        Assert.assertTrue(page.isMaleRadioButtonVisible(), "Male radio button not visible");
        Assert.assertTrue(page.isFemaleRadioButtonVisible(), "Female radio button not visible");
        Assert.assertTrue(page.isSavePersonalDetailsButtonVisible(), "Save button not visible");

        Assert.assertTrue(page.isBloodTypeDropdownVisible(), "Blood Type dropdown not visible");
        Assert.assertTrue(page.isEmployeeTestFieldVisible(), "Custom field 'Test' not visible");
        Assert.assertTrue(page.isSaveCustomFieldsButtonVisible(), "Save custom fields button not visible");

        logger.info("All Personal and Custom Fields verified as visible.");
    }

    @Test(description = "Validate mandatory fields display error if empty",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validation messages should be shown when mandatory fields are empty")
    public void validateMandatoryFieldsOnPersonalDetailsPage() {
        logger.info("Starting test: validateMandatoryFieldsOnPersonalDetailsPage");

        EditPersonalDetailPage page = navigateToEditEmployeePage();
        page.clearFirstNameField();
        page.clearLastNameField();

        String lastNameError = page.getErrorMessageLastName();
        Assert.assertTrue(lastNameError.toLowerCase().contains("required") || lastNameError.toLowerCase().contains("cannot be empty"),
                "Validation message for Last Name is missing or incorrect");

        logger.info("Validation error messages verified for mandatory fields.");
    }

    @Test(description = "Validate saving of valid personal details",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to save valid personal data")
    public void validateSavePersonalDetailsWithValidInputs() {
        logger.info("Starting test: validateSavePersonalDetailsWithValidInputs");

        EditPersonalDetailPage page = navigateToEditEmployeePage();

        page.enterEmployeeFirstName("Harshal");
        page.enterEmployeeMiddleName("Shivaji");
        page.enterEmployeeLastName("Thitame");
        page.enterOtherId("EMP1618");
        page.enterLicenseNumber("DLX789456");
        page.enterExpiryDate("2030-12-31");
        page.selectNationality("Indian");
        page.selectMaritalStatus("Single");
        page.enterDOBDate("2000-02-16");
        page.selectMaleGender();

        page.clickSavePersonalDetails();

        String toast = page.getSuccessToastMessage();
        Assert.assertTrue(toast.toLowerCase().contains("success"), "Toast message did not indicate success");

        logger.info("Personal details saved and verified successfully.");
    }

    @Test(description = "Validate saving of custom fields",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to save custom fields with valid data")
    public void validateSavingCustomFieldsWithValidData() {
        logger.info("Starting test: validateSavingCustomFieldsWithValidData");

        EditPersonalDetailPage page = navigateToEditEmployeePage();
        page.selectBloodType("O+");
        page.enterTestField("Automation Tester");

        page.clickSaveCustomFields();

        String toast = page.getSuccessToastMessage();
        Assert.assertTrue(toast.toLowerCase().contains("success"), "Toast message did not indicate success");

        logger.info("Custom fields saved successfully.");
    }

    @Test(description = "Validate only one gender can be selected",
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("Only one gender option should be selectable at a time")
    public void validateOnlyOneGenderCanBeSelected() {
        logger.info("Starting test: validateOnlyOneGenderCanBeSelected");

        EditPersonalDetailPage page = navigateToEditEmployeePage();

        page.selectMaleGender();
        Assert.assertTrue(page.isMaleGenderSelected(), "Male should be selected");
        Assert.assertFalse(page.isFemaleGenderSelected(), "Female should not be selected");

        page.selectFemaleGender();
        Assert.assertTrue(page.isFemaleGenderSelected(), "Female should be selected");
        Assert.assertFalse(page.isMaleGenderSelected(), "Male should not be selected");

        logger.info("Gender selection works correctly - mutually exclusive.");
    }
}
