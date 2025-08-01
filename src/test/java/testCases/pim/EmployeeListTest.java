package testCases.pim;

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
import utils.RetryAnalyzer;

@Epic("PIM Module")
@Feature("Validate Employee List Functionality")
public class EmployeeListTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeListTest.class);

    @Test(
            description = "Verify all search fields are visible on Employee List page",
            priority = 1,
            groups = {"Smoke", "Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("All search input fields and dropdowns should be visible on Employee List page")
    public void verifyAllSearchFieldsAreVisible() {

        logger.info("===== Starting test: verifyAllSearchFieldsAreVisible =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();
        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();

        logger.info("Verifying visibility of search input fields and buttons...");

        Assert.assertTrue(employeeListPage.isEmployeeNameVisible(), "Employee Name field not visible");
        Assert.assertTrue(employeeListPage.isEmployeeIdVisible(), "Employee ID field not visible");
        Assert.assertTrue(employeeListPage.isSearchButtonVisible(), "Search button not visible");
        Assert.assertTrue(employeeListPage.isResetButtonVisible(), "Reset button not visible");

        logger.info("All expected search fields are visible on Employee List page.");
        logger.info("===== Finished test: verifyAllSearchFieldsAreVisible =====");

    }

    @Test(
            description = "Search employee by name from Employee List",
            priority = 2,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search functionality should allow filtering employees by name")
    public void searchEmployeeByName() {

        logger.info("===== Starting test: searchEmployeeByName =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();

        // Step 3: Search for employee by name
        String employeeName = ConfigReader.get("emp.name");  // Visible in your screenshot
        logger.info("Searching for employee: " + employeeName);
        employeeListPage.enterEmployeeName(employeeName);
        employeeListPage.clickSearchButton();

        // Step 4: Verify that the result contains the searched employee
        String actualResult = employeeListPage.getFirstResultName();
        logger.info("Search Result Name: " + actualResult);
        Assert.assertTrue(actualResult.contains(ConfigReader.get("emp.name")) || actualResult.equalsIgnoreCase(employeeName),
                "Search result does not match the expected employee name");

        logger.info("Employee search by name verified successfully.");
        logger.info("===== Finished test: searchEmployeeByName =====");
    }

    @Test(
            description = "Search employee by valid Employee ID",
            priority = 3,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Search functionality should return correct employee when searched by Employee ID")
    public void searchEmployeeById() {

        logger.info("===== Starting test: searchEmployeeById =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();

        // Step 3: Enter valid Employee ID
        String validEmployeeId = ConfigReader.get("existingEmployeeId"); // e.g., "0429"
        employeeListPage.enterEmployeeId(validEmployeeId);

        logger.info("Entered Employee ID: " + validEmployeeId);

        // Step 4: Click on Search
        employeeListPage.clickSearchButton();

        // Step 5: Verify search results
        logger.info("Verifying search results for employee ID...");
        String actualEmployeeId = employeeListPage.getEmployeeIdFromFirstRow();

        Assert.assertEquals(actualEmployeeId, validEmployeeId,
                "Search result does not match the entered Employee ID");

        logger.info("Search by Employee ID verified successfully.");
        logger.info("===== Finished test: searchEmployeeById =====");
    }

    @Test(
            description = "Verify employee search works correctly with combined filters - ID + Name",
            priority = 8,
            groups = {"Regression", "Sanity"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Employee list search should return accurate results when combining ID and Name filters")
    public void verifySearchWithCombinedIdAndNameFilters() {

        logger.info("===== Starting test: verifySearchWithCombinedIdAndNameFilters =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();

        // Step 3: Set filters
        String employeeId = ConfigReader.get("existingEmployeeId");
        String employeeName = ConfigReader.get("emp.name");

        logger.info("Entering Employee ID: " + employeeId);
        employeeListPage.enterEmployeeId(employeeId);

        logger.info("Entering Employee Name: " + employeeName);
        employeeListPage.enterEmployeeName(employeeName);

        // Step 4: Click Search
        employeeListPage.clickSearchButton();

        // Step 5: Validate results
        logger.info("Verifying filtered result contains correct ID and Name...");
        String name = employeeListPage.getFirstResultName();
        String id = employeeListPage.getEmployeeIdFromFirstRow();

        logger.info("Expected ID: " + employeeId + " | Found ID: " + id);
        logger.info("Expected Name: " + employeeName + " | Found Name: " + name);

        Assert.assertEquals(id.trim(), employeeId, "Employee ID does not match.");
        Assert.assertTrue(name.contains(employeeName), "Employee Name does not match.");

        logger.info("===== Finished test: verifySearchWithCombinedIdAndNameFilters =====");
    }

    @Test(
            description = "Verify that Reset button clears all search filter fields on Employee List page",
            priority = 9,
            groups = {"Sanity", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Reset button should clear all entered filters in Employee List search form")
    public void verifyResetSearchFiltersFunctionality() {

        logger.info("===== Starting test: verifyResetSearchFiltersFunctionality =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();

        logger.info("Filling search filters with sample values...");
        // Step 3: Fill in some search filters
        employeeListPage.enterEmployeeName(ConfigReader.get("emp.name"));
        employeeListPage.enterEmployeeId(ConfigReader.get("existingEmployeeId"));


        // Step 4: Click Reset button
        logger.info("Clicking Reset button...");
        employeeListPage.clickResetButton();

        // Step 5: Verify that all filters are cleared
        logger.info("Verifying that all filters are cleared...");
        Assert.assertEquals(employeeListPage.getEmployeeName(), "", "Employee Name not cleared");
        Assert.assertEquals(employeeListPage.getEmployeeId(), "", "Employee ID not cleared");


        logger.info("Reset functionality validated successfully.");
        logger.info("===== Finished test: verifyResetSearchFiltersFunctionality =====");
    }

}
