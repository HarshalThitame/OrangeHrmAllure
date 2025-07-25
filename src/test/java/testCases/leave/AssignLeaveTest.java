package testCases.leave;

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
import pageObjects.leave.AssignLeavePage;
import utils.RetryAnalyzer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssignLeaveTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(AssignLeaveTest.class);

    @Test(
            description = "Verify all input fields are present on Assign Leave page",
            priority = 1,
            groups = {"Smoke", "Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("UI elements should be visible on Assign Leave form")
    public void verifyAssignLeavePageFields() {

        logger.info("===== Starting test: verifyAssignLeavePageFields =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");


        logger.info("Login successful. Navigating to Leave > Assign Leave page...");

        // Step 2: Navigate to Assign Leave
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Verify all form fields are present
        logger.info("Verifying visibility of form elements on Assign Leave page...");
        Assert.assertTrue(assignLeavePage.isEmployeeNameVisible(), "Employee Name field is missing");
        Assert.assertTrue(assignLeavePage.isLeaveTypeDropdownVisible(), "Leave Type dropdown is missing");
        Assert.assertTrue(assignLeavePage.isFromDateFieldVisible(), "From Date field is missing");
        Assert.assertTrue(assignLeavePage.isToDateFieldVisible(), "To Date field is missing");
        Assert.assertTrue(assignLeavePage.isCommentsFieldVisible(), "Comments textarea is missing");
        Assert.assertTrue(assignLeavePage.isAssignButtonVisible(), "Assign button is missing");

        logger.info("All required fields verified on Assign Leave page.");
        logger.info("===== Finished test: verifyAssignLeavePageFields =====");
    }

    @Test(
            description = "Verify error messages when required fields are left empty on Assign Leave page",
            priority = 2,
            groups = {"Smoke", "Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Form validation should display errors when required fields are empty")
    public void verifyAssignLeaveFormValidationOnEmptyFields() {

        logger.info("===== Starting test: verifyAssignLeaveFormValidationOnEmptyFields =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");

        logger.info("Login successful. Navigating to Leave > Assign Leave page...");

        // Step 2: Navigate to Assign Leave
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Submit the form without entering any data
        logger.info("Clicking on Assign button without entering any required fields");
        assignLeavePage.clickAssignButton();

        // Step 4: Validate required field error messages
        logger.info("Validating error messages for required fields...");

        Assert.assertEquals(assignLeavePage.getEmployeeNameRequiredMessage().trim(), "Required", "Employee Name validation message mismatch");
        Assert.assertEquals(assignLeavePage.getLeaveTypeRequiredMessage().trim(), "Required", "Leave Type validation message mismatch");
        Assert.assertEquals(assignLeavePage.getFromDateRequiredMessage().trim(), "Required", "From Date validation message mismatch");
        Assert.assertEquals(assignLeavePage.getToDateRequiredMessage().trim(), "Required", "To Date validation message mismatch");

        logger.info("Required field validations verified successfully.");
        logger.info("===== Finished test: verifyAssignLeaveFormValidationOnEmptyFields =====");
    }

    @Test(
            description = "Assign leave with valid employee details and date range",
            priority = 3,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.BLOCKER)
    @Story("Successfully assign leave with valid data")
    public void assignLeaveWithValidData() {

        logger.info("===== Starting test: assignLeaveWithValidData =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");

        logger.info("Login successful. Navigating to Leave > Assign Leave page...");

        // Step 2: Navigate to Assign Leave
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Fill in valid data
        logger.info("Filling form with valid data...");
        assignLeavePage.enterEmployeeName(ConfigReader.get("emp.name"));
        assignLeavePage.selectLeaveType("CAN - Vacation");
        assignLeavePage.selectFromDate("15", "July", "2025");
//        assignLeavePage.selectToDate(25, 8, 2025);
        assignLeavePage.enterComment("Family medical leave");

        // Step 4: Submit the form
        logger.info("Submitting leave assignment...");
        assignLeavePage.clickAssignButton();
        logger.info("Confirming leave assignment...");
        assignLeavePage.confirmAssignButton();

        // Step 5: Validate success message
        logger.info("Verifying leave assignment success message...");
        String successMessage = assignLeavePage.getSuccessToastMessage();
        Assert.assertTrue(successMessage.contains("Success"), "Leave assignment failed or success message not displayed");

        logger.info("Leave assigned successfully with valid data.");
        logger.info("===== Finished test: assignLeaveWithValidData =====");
    }

    @Test(
            description = "Verify that past dates cannot be selected in From Date or To Date fields",
            priority = 4,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Form should not allow selection of past dates for leave assignment")
    public void verifyPastDateSelectionNotAllowed() {

        logger.info("===== Starting test: verifyPastDateSelectionNotAllowed =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");

        logger.info("Login successful. Navigating to Assign Leave page...");

        // Step 2: Navigate to Assign Leave page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Try to enter a past date (e.g., yesterday) in From Date and To Date
        String pastDate = DateUtils.getDate("yyyy-MMMM-dd", 1); // returns yesterday's date in correct format

        logger.info("Trying to set past From and To dates: " + pastDate);
        String[] date = pastDate.split("-");
        assignLeavePage.selectFromDate(date[2], date[1], date[0]);
//        assignLeavePage.selectToDate(date[2], date[1], date[0]);

        // Step 4: Try to submit the form
        assignLeavePage.clickAssignButton();

        // Step 5: Validate error or restriction message
        logger.info("Checking validation for past dates...");
        String errorMsg = assignLeavePage.getSuccessToastMessage();
        Assert.assertTrue(errorMsg.contains("Cannot assign leave for past date") || errorMsg.contains("Invalid date"),
                "Expected validation message for past date not displayed. Message found: " + errorMsg);

        logger.info("Past date validation verified successfully.");
        logger.info("===== Finished test: verifyPastDateSelectionNotAllowed =====");
    }

    @Test(
            description = "Verify leave can be assigned successfully with valid data",
            priority = 5,
            groups = {"Smoke", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Assign leave successfully by providing all valid inputs")
    public void verifyAssignLeaveWithValidData() {

        logger.info("===== Starting test: verifyAssignLeaveWithValidData =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");

        logger.info("Login successful. Navigating to Assign Leave page...");

        // Step 2: Navigate to Assign Leave page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Fill the form with valid data
        String leaveType = "CAN - Personal";

        logger.info("Entering valid employee name: {}", ConfigReader.get("emp.name"));
        assignLeavePage.enterEmployeeName(ConfigReader.get("emp.name"));

        logger.info("Selecting leave type: {}", leaveType);
        assignLeavePage.selectLeaveType(leaveType);

        logger.info("Selecting valid From Date and To Date (today and tomorrow)");
        String fromDate = DateUtils.getDate("yyyy-MMMM-dd", 1);
//        String toDate = DateUtils.getFutureDate("yyyy-MMMM-dd", 1);

        String[] from = fromDate.split("-");
//        String[] to = toDate.split("-");

        assignLeavePage.selectFromDate(from[2], from[1], from[0]);
//        assignLeavePage.selectToDate(to[2], to[1], to[0]);

        logger.info("Entering leave comment...");
        assignLeavePage.enterComment("Automation leave assignment test.");

        // Step 4: Submit the form
        assignLeavePage.clickAssignButton();
        assignLeavePage.confirmAssignButton();

        // Step 5: Validate success message
        logger.info("Verifying leave assigned success message...");
        String successMessage = assignLeavePage.getSuccessToastMessage();

        Assert.assertTrue(successMessage.contains("Success"),
                "Expected success message not displayed. Actual: " + successMessage);

        logger.info("Leave assigned successfully with message: {}", successMessage);
        logger.info("===== Finished test: verifyAssignLeaveWithValidData =====");
    }

    @Test(
            description = "Verify validation when From Date is after To Date on Assign Leave page",
            priority = 6,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Form should validate that From Date is not after To Date")
    public void verifyValidationWhenFromDateIsAfterToDate() {

        logger.info("===== Starting test: verifyValidationWhenFromDateIsAfterToDate =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");

        logger.info("Login successful. Navigating to Assign Leave page...");

        // Step 2: Navigate to Assign Leave page
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickQuickLaunch("Assign Leave");
        AssignLeavePage assignLeavePage = dashboardPage.goToAssignLeavePage();

        // Step 3: Enter valid Employee Name and Leave Type
        String employeeName = ConfigReader.get("emp.name");
        String leaveType = "CAN - Personal";

        assignLeavePage.enterEmployeeName(employeeName);
        assignLeavePage.selectLeaveType(leaveType);

        // Step 4: Set From Date as 2 days in future, To Date as today (invalid case)
        String fromDate = DateUtils.getDate("yyyy-MMMM-dd", -2); // 2 days from now
        String toDate = DateUtils.getDate("yyyy-MM-dd", 0);     // today

        String[] from = fromDate.split("-");
        String[] to = toDate.split("-");

        logger.info("Setting From Date (future): {}", fromDate);
        logger.info("Setting To Date (today): {}", toDate);

        assignLeavePage.selectFromDate(from[2], from[1], from[0]);
        assignLeavePage.selectToDate(to[2], to[1], to[0]);

        assignLeavePage.enterComment("Testing invalid date range");

        // Step 6: Validate error or restriction
        logger.info("Checking validation message for From Date > To Date...");
        String errorMsg = assignLeavePage.getInvalidDateRangeMessage(); // This could be a popup or toast


        Assert.assertTrue(errorMsg.contains("To date should be after from date"),
                "Validation message for incorrect date range not shown. Actual: " + errorMsg);


        logger.info("From Date > To Date validation verified successfully.");
        logger.info("===== Finished test: verifyValidationWhenFromDateIsAfterToDate =====");
    }

    public static class DateUtils {

        public static String getDate(String format, int daysBeforeToday) {
            LocalDate date = LocalDate.now().minusDays(daysBeforeToday);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return date.format(formatter);
        }
    }

}
