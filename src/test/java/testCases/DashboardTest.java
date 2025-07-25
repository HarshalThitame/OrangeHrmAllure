package testCases;


import base.BaseTest;
import factory.DriverFactory;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.RetryAnalyzer;

@Epic("Dashboard")
@Feature("Verify Dashboard Feature")
public class DashboardTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DashboardTest.class);

    @Test(
            description = "Verify dashboard loads correctly after login",
            priority = 1,
            groups = {"Smoke", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify dashboard components are visible after login")
    public void verifyDashboardLoadedSuccessfully() {
        logger.info("===== Starting test: verifyDashboardLoadedSuccessfully =====");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();

        logger.info("Login successful. Checking visibility of key dashboard widgets...");

        Assert.assertTrue(dashboardPage.isTimeAtWorkWidgetVisible(), "'Time at Work' widget not displayed");
        Assert.assertTrue(dashboardPage.isQuickLaunchSectionVisible(), "'Quick Launch' section not visible");
        Assert.assertTrue(dashboardPage.isMyActionsWidgetVisible(), "'My Actions' widget missing");

        logger.info("All major dashboard widgets verified.");
        logger.info("===== Finished test: verifyDashboardLoadedSuccessfully =====");
    }


    @Test(
            description = "Verify 'Time at Work' widget is displayed on Dashboard",
            priority = 2,
            groups = {"Sanity", "Smoke"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Story("Check visibility of 'Time at Work' widget on dashboard")
    public void verifyTimeAtWorkWidgetDisplayed() {

        logger.info("===== Starting test: verifyTimeAtWorkWidgetDisplayed =====");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();

        logger.info("Logged in successfully. Verifying 'Time at Work' widget...");

        boolean isDisplayed = dashboardPage.isTimeAtWorkWidgetVisible();
        Assert.assertTrue(isDisplayed, "'Time at Work' widget is not visible on dashboard");

        logger.info("'Time at Work' widget verified successfully.");
        logger.info("===== Finished test: verifyTimeAtWorkWidgetDisplayed =====");
    }

    @Test(
            description = "Verify Quick Launch buttons navigate to correct modules",
            priority = 3,
            groups = {"Smoke", "Regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Story("Ensure Quick Launch icons navigate correctly")
    public void verifyQuickLaunchButtonsFunctionality() {

        logger.info("===== Starting test: verifyQuickLaunchButtonsFunctionality =====");

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");
        DashboardPage dashboardPage = loginPage.navigateToDashBoard();

        logger.info("Clicking 'Assign Leave' button...");
        dashboardPage.clickQuickLaunch("Assign Leave");
        Assert.assertTrue(dashboardPage.isAssignLeavePageOpened(), "'Assign Leave' page did not open");

        logger.info("Returning to Dashboard and clicking 'Timesheets'...");
        dashboardPage.navigateToDashboard();
        dashboardPage.clickQuickLaunch("Timesheets");
        Assert.assertTrue(dashboardPage.isTimesheetPageOpened(), "'Timesheets' page did not open");

        logger.info("Quick Launch button navigation verified successfully.");
        logger.info("===== Finished test: verifyQuickLaunchButtonsFunctionality =====");
    }

}
