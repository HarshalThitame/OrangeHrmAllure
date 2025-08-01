package pageObjects.leave;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AssignLeavePage extends BasePage {

    // Dashboard header validation
    By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");

    // Auto-suggest input field (e.g., employee name)
    By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");

    // Select input dropdown (e.g., Leave Type)
    By leaveTypeDropdown = By.xpath("//div[@class='oxd-select-text-input']");

    // From Date calendar icon
    By fromDateCalendarIcon = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div/i");

    // To Date calendar icon
    By toDateCalendarIcon = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div/i");

    // Comments textarea
    By commentsTextarea = By.xpath("//*[@id='app']//textarea");

    // Assign button
    By assignButton = By.xpath("//button[normalize-space()='Assign']");
    By confirmButton = By.xpath("//button[normalize-space()='Ok']");

    // Employee Name required error message
    By employeeNameRequiredError = By.xpath("//div[@class='orangehrm-card-container']//div[1]//div[1]//div[1]//div[1]//span[1]");

    // Leave Type required error message
    By leaveTypeRequiredError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/span");

    // From Date required error message
    By fromDateRequiredError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/span");

    // To Date required error message
    By toDateRequiredError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/span");

    // From Date and To Date input fields using corrected placeholder
    By fromDateInputField = By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]");
    By toDateInputField = By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]");

    By successToastMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    By invalidDateRangeError = By.xpath("//span[contains(normalize-space(), 'To date should be after')]");

    public AssignLeavePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if Employee Name field is visible")
    public boolean isEmployeeNameVisible() {
        return isElementVisible(employeeNameInput);
    }

    @Step("Check if Leave Type dropdown is visible")
    public boolean isLeaveTypeDropdownVisible() {
        return isElementVisible(leaveTypeDropdown);
    }

    @Step("Check if From Date field is visible")
    public boolean isFromDateFieldVisible() {
        return isElementVisible(fromDateCalendarIcon);
    }

    @Step("Check if To Date field is visible")
    public boolean isToDateFieldVisible() {
        return isElementVisible(toDateCalendarIcon);
    }

    @Step("Check if Comments textarea is visible")
    public boolean isCommentsFieldVisible() {
        return isElementVisible(commentsTextarea);
    }

    @Step("Check if Assign button is visible")
    public boolean isAssignButtonVisible() {
        return isElementVisible(assignButton);
    }


    @Step("Click on 'Assign' button")
    public void clickAssignButton() {
        wait.until(ExpectedConditions.elementToBeClickable(assignButton)).click();
    }

    @Step("Confirming assign leave")
    public void confirmAssignButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton)).click();
    }

    @Step("Get Employee Name required message")
    public String getEmployeeNameRequiredMessage() {
        return driver.findElement(employeeNameRequiredError).getText();
    }

    @Step("Get Leave Type required message")
    public String getLeaveTypeRequiredMessage() {
        return driver.findElement(leaveTypeRequiredError).getText();
    }

    @Step("Get From Date required message")
    public String getFromDateRequiredMessage() {
        return driver.findElement(fromDateRequiredError).getText();
    }

    @Step("Get To Date required message")
    public String getToDateRequiredMessage() {
        return driver.findElement(toDateRequiredError).getText();
    }

    @Step("Enter employee name: {name}")
    public void enterEmployeeName(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
        input.clear();
        input.sendKeys(name);
        // Optional: select from auto-suggest if needed
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", name));
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionOption));
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
    }

    @Step("Select leave type: {leaveType}")
    public void selectLeaveType(String leaveType) {
        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeDropdown)).click();
        By option = By.xpath(String.format("//*[text()='%s']", leaveType));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    @Step("Select From Date: {date}")
    public void selectFromDate(String day, String month, String year) {
        int dayInt = Integer.parseInt(day);
        String dayString = String.valueOf(dayInt);
        wait.until(ExpectedConditions.elementToBeClickable(fromDateCalendarIcon)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-calendar-selector-month-selected']//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//li[normalize-space()='%s']", month)))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-calendar-selector-year-selected']//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//li[normalize-space()='%s']", year)))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[normalize-space()='%s']", dayString)))).click();

    }

    @Step("Select To Date: {date}")
    public void selectToDate(String day, String month, String year) {
        int dayInt = Integer.parseInt(day);
        String dayString = String.valueOf(dayInt);
//        wait.until(ExpectedConditions.elementToBeClickable(toDateCalendarIcon)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div/i"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//li[normalize-space()='%s']", month)))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-calendar-selector-month-selected']//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon']"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//li[normalize-space()='%s']", year)))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[normalize-space()='%s']", day)))).click();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div/input")));
        input.clear();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(year + "-" + day + "-" + month);
        input.sendKeys(Keys.TAB);

    }


    @Step("Enter comment: {comment}")
    public void enterComment(String comment) {
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(commentsTextarea));
        textarea.clear();
        textarea.sendKeys(comment);
    }


    @Step("Get invalid date range validation message")
    public String getInvalidDateRangeMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidDateRangeError));
            return errorElement.getText().trim();
        } catch (TimeoutException e) {
            return "No invalid date range message displayed";
        }
    }


}
