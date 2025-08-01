package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.DashboardPage;
import pageObjects.leave.AssignLeavePage;
import pageObjects.pim.AddEmployeePage;
import pageObjects.pim.EmployeeListPage;
import pageObjects.pim.PIMPage;
import pageObjects.pim.editEmployeeDetails.EditContactDetailsPage;
import pageObjects.pim.editEmployeeDetails.EditEmployeePage;
import pageObjects.pim.editEmployeeDetails.EditPersonalDetailPage;
import pageObjects.pim.editEmployeeDetails.EditSalaryDetailsPage;

import java.time.Duration;

public class BasePage {
    protected static Logger log;
    protected WebDriver driver;
    protected WebDriverWait wait;
    By successToastMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = LoggerFactory.getLogger(this.getClass());
    }

    protected boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Element not visible within timeout: " + locator);
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + locator);
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error checking visibility of element: " + locator);
            e.printStackTrace();
            return false;
        }
    }


    protected void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on element: " + locator, e);
        }
    }

    protected void enterText(By locator, String text) {
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
            webElement.click();
            webElement.sendKeys(Keys.CONTROL, "a");
            webElement.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter text into element: " + locator, e);
        }
    }

    protected String getText(By locator) {
        try {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return webElement.getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the text into element: " + locator, e);
        }
    }

    protected void clearField(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear field: " + locator, e);
        }
    }

    public String getSuccessToastMessage() {
        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(successToastMessage));
            return toast.getText().trim();
        } catch (TimeoutException e) {
            return "No success toast appeared";
        }
    }

    public DashboardPage navigateToDashBoard() {
        return new DashboardPage(driver);
    }


    public AssignLeavePage goToAssignLeavePage() {
        return new AssignLeavePage(driver);
    }

    public PIMPage goToPIMPage() {
        return new PIMPage(driver);
    }

    public AddEmployeePage goToAddEmployeePage() {
        return new AddEmployeePage(driver);
    }

    public EmployeeListPage goToEmployeeListPage() {
        return new EmployeeListPage(driver);
    }

    public EditEmployeePage goToEditEmployeePage() {
        return new EditEmployeePage(driver);
    }

    public EditPersonalDetailPage goToEditPersonalDetailPage() {
        return new EditPersonalDetailPage(driver);
    }

    public EditContactDetailsPage goToEditContactDetailsPage() {
        return new EditContactDetailsPage(driver);
    }

    public EditSalaryDetailsPage goToEditSalaryDetailsPage() {
        return new EditSalaryDetailsPage(driver);
    }

}
