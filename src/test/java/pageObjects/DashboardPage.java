package pageObjects;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.pim.AddEmployeePage;

public class DashboardPage extends BasePage {

    By validationText = By.xpath("//h6[normalize-space()='Dashboard']");
    By userProfileDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    By aboutButton = By.xpath("//a[normalize-space()='About']");
    By supportButton = By.xpath("//a[normalize-space()='Support']");
    By changePasswordButton = By.xpath("//a[normalize-space()='Change Password']");
    By loginButton = By.xpath("//a[normalize-space()='Logout']");
    By timeAtWorkWidget = By.xpath("//p[contains(.,'Time at Work')]/parent::div[contains(@class,'orangehrm-dashboard-widget')]");
    By quickLaunchSection = By.xpath("//p[normalize-space()='Quick Launch']");
    By assignLeavePageText = By.xpath("//h6[normalize-space()='Assign Leave']");
    By dashboardPageButton = By.xpath("//span[normalize-space()='Dashboard']");
    By timesheetHeader = By.xpath("//span[contains(text(),'Timesheets')]");
    By pimMenu = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']");
    By myActionsWidget = By.xpath("//p[text()='My Actions']/ancestor::div[contains(@class,'orangehrm-dashboard-widget')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get dashboard text for validation ")
    public String getValidationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(validationText)).getText();
    }

    @Step("Click on user profile dropdown")
    public void clickOnUserProfileDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(userProfileDropdown)).click();
    }

    @Step("Click on about button")
    public void clickOnAboutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(aboutButton)).click();
    }

    @Step("Click on support button")
    public void clickOnSupportButton() {
        wait.until(ExpectedConditions.elementToBeClickable(supportButton)).click();
    }

    @Step("Click on change password button")
    public void clickOnChangePasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordButton)).click();
    }

    @Step("CLick on logout button")
    public void clickOnLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Perform logout")
    public void performLogout() {
        clickOnUserProfileDropdown();
        clickOnLogoutButton();
    }

    @Step("Check if 'Time at Work' widget is visible")
    public boolean isTimeAtWorkWidgetVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(timeAtWorkWidget));
            return driver.findElement(timeAtWorkWidget).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Check if 'Quick Launch' section is visible")
    public boolean isQuickLaunchSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(quickLaunchSection));
            return driver.findElement(quickLaunchSection).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Check if 'My Actions' widget is visible")
    public boolean isMyActionsWidgetVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(myActionsWidget));
            return driver.findElement(myActionsWidget).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Click Quick Launch option: {optionName}")
    public void clickQuickLaunch(String optionName) {
        String quickLaunchOptionXpath = "//button[@title='%s']//*[name()='svg']";
        By optionLocator = By.xpath(String.format(quickLaunchOptionXpath, optionName));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    @Step("Verify if 'Assign Leave' page is opened")
    public boolean isAssignLeavePageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(assignLeavePageText));
            return driver.findElement(assignLeavePageText).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Navigate to Dashboard via left menu")
    public void navigateToDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPageButton));
    }


    @Step("Verify if 'Timesheet' page is opened")
    public boolean isTimesheetPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(timesheetHeader));
            return driver.findElement(timesheetHeader).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Click on PIM menu")
    public void clickOnPimMenu() {
        WebElement pim = wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        pim.click();
    }

    public AddEmployeePage goToAddEmployeePage() {
        return new AddEmployeePage(driver);
    }
}
