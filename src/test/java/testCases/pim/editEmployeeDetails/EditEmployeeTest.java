package testCases.pim.editEmployeeDetails;

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
import pageObjects.pim.EmployeeListPage;
import pageObjects.pim.PIMPage;
import pageObjects.pim.editEmployeeDetails.EditEmployeePage;
import utils.RetryAnalyzer;

public class EditEmployeeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(EditEmployeeTest.class);

    @Test(
            description = "Verify that all employee profile tabs are visible on Edit Employee page",
            priority = 1,
            groups = {"Smoke", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("All profile section tabs should be visible on the Edit Employee page")
    public void verifyAllEmployeeTabsVisible() {

        logger.info("===== Starting test: verifyAllEmployeeTabsVisible =====");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Step 2: Navigate to PIM > Employee List
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();
        dashboardPage.clickOnPimMenu();
        PIMPage pimPage = dashboardPage.goToPIMPage();
        pimPage.clickEmployeeListButton();

        // Step 3: Select an employee to view/edit
        EmployeeListPage employeeListPage = pimPage.goToEmployeeListPage();
        String employeeName = ConfigReader.get("emp.name");
        employeeListPage.enterEmployeeName(employeeName);
        employeeListPage.clickSearchButton();

        employeeListPage.clickEditButton(); // implement this method if needed

        // Step 4: Now on Edit Employee Page
        EditEmployeePage editEmployeePage = employeeListPage.goToEditEmployeePage();

        // Step 5: Validate all tabs are visible
        Assert.assertTrue(editEmployeePage.isPersonalDetailsTabVisible(), "Personal Details tab not visible");
        Assert.assertTrue(editEmployeePage.isContactDetailsTabVisible(), "Contact Details tab not visible");
        Assert.assertTrue(editEmployeePage.isEmergencyContactsTabVisible(), "Emergency Contacts tab not visible");
        Assert.assertTrue(editEmployeePage.isDependentsTabVisible(), "Dependents tab not visible");
        Assert.assertTrue(editEmployeePage.isImmigrationTabVisible(), "Immigration tab not visible");
        Assert.assertTrue(editEmployeePage.isJobTabVisible(), "Job tab not visible");
        Assert.assertTrue(editEmployeePage.isSalaryTabVisible(), "Salary tab not visible");
        Assert.assertTrue(editEmployeePage.isReportToTabVisible(), "Report-To tab not visible");
        Assert.assertTrue(editEmployeePage.isQualificationsTabVisible(), "Qualifications tab not visible");
        Assert.assertTrue(editEmployeePage.isMembershipsTabVisible(), "Memberships tab not visible");

        logger.info("All employee profile tabs are visible as expected.");
        logger.info("===== Finished test: verifyAllEmployeeTabsVisible =====");
    }

}
