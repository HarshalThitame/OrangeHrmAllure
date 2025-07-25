package pageObjects.pim;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {

    By firstNameField = By.name("firstName");
    By middleNameField = By.name("middleName");
    By lastNameField = By.name("lastName");

    By eidField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    By createLoginDetailsRadioButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");

    By usernameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input");
    By passwordField = By.xpath("(//input[@type='password'])[1]");
    By confirmPasswordField = By.xpath("(//input[@type='password'])[2]");
    By enabledRadioButton = By.xpath("//label[normalize-space()='Enabled']//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']");
    By disabledRadioButton = By.xpath("//label[normalize-space()='Disabled']//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']");
    By submitButton = By.xpath("//button[@type='submit']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");

    By firstNameRequiredMessage = By.xpath("//div[@class='oxd-input-group']//div[1]//span[1]");
    By lastNameRequiredMessage = By.xpath("//div[@class='--name-grouped-field']//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']");
    By usernameRequiredMessage = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']");
    By passwordRequiredMessage = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']");
    By confirmPasswordNotMatchedMessage = By.xpath("//span[normalize-space()='Passwords do not match']");
    By eidAlreadyExistMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    By usernameAlreadyExistMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    By successToastMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");


    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get required message for First Name field")
    public String getFirstNameRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameRequiredMessage)).getText().trim();
    }

    @Step("Get required message for Last Name field")
    public String getLastNameRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameRequiredMessage)).getText().trim();
    }

    @Step("Get required message for Username field")
    public String getUsernameRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredMessage)).getText().trim();
    }

    @Step("Get required message for Password field")
    public String getPasswordRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRequiredMessage)).getText().trim();
    }

    @Step("Get 'Passwords do not match' validation message")
    public String getConfirmPasswordNotMatchedMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordNotMatchedMessage)).getText().trim();
    }

    @Step("Get 'Employee Id already exists' validation message")
    public String getEidAlreadyExistMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(eidAlreadyExistMessage)).getText().trim();
    }

    @Step("Get 'Username already exists' validation message")
    public String getUsernameAlreadyExistMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameAlreadyExistMessage)).getText().trim();
    }

    @Step("Enter First Name: {firstName}")
    public void enterFirstName(String firstName) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);
    }

    @Step("Enter Middle Name: {middleName}")
    public void enterMiddleName(String middleName) {
        WebElement middleNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(middleNameField));
        middleNameElem.clear();
        middleNameElem.sendKeys(middleName);
    }

    @Step("Enter Last Name: {lastName}")
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    @Step("Enter Employee ID: {empId}")
    public void enterEmployeeId(String empId) {
        WebElement eidElem = wait.until(ExpectedConditions.visibilityOfElementLocated(eidField));
        eidElem.clear();
        eidElem.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        eidElem.sendKeys(empId);
    }

    @Step("Click 'Create Login Details' checkbox")
    public void clickCreateLoginDetailsCheckbox() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(createLoginDetailsRadioButton));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Step("Enter Username: {username}")
    public void enterUsername(String username) {
        WebElement usernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElem.clear();
        usernameElem.sendKeys(username);
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElem.clear();
        passwordElem.sendKeys(password);
    }

    @Step("Enter Confirm Password")
    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        confirmPasswordElem.clear();
        confirmPasswordElem.sendKeys(confirmPassword);
    }

    @Step("Select Account Status: {status}")
    public void selectAccountStatus(String status) {
        if (status.equalsIgnoreCase("enabled")) {
            WebElement enabled = wait.until(ExpectedConditions.elementToBeClickable(enabledRadioButton));
            enabled.click();
        } else if (status.equalsIgnoreCase("disabled")) {
            WebElement disabled = wait.until(ExpectedConditions.elementToBeClickable(disabledRadioButton));
            disabled.click();
        }
    }

    @Step("Click Save button")
    public void clickSubmitButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        saveBtn.click();
    }

    @Step("Click Cancel button")
    public void clickCancelButton() {
        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelBtn.click();
    }

    @Step("Check if First Name field is visible")
    public boolean isFirstNameVisible() {
        return isElementVisible(firstNameField);
    }

    @Step("Check if Middle Name field is visible")
    public boolean isMiddleNameVisible() {
        return isElementVisible(middleNameField);
    }

    @Step("Check if Last Name field is visible")
    public boolean isLastNameVisible() {
        return isElementVisible(lastNameField);
    }

    @Step("Check if Employee ID field is visible")
    public boolean isEmployeeIdVisible() {
        return isElementVisible(eidField);
    }

    @Step("Check if Save button is visible")
    public boolean isSaveButtonVisible() {
        return isElementVisible(submitButton);
    }

    @Step("Check if Cancel button is visible")
    public boolean isCancelButtonVisible() {
        return isElementVisible(cancelButton);
    }

    @Step("Toggle 'Create Login Details' checkbox")
    public void toggleCreateLoginDetails() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(createLoginDetailsRadioButton));
        checkbox.click(); // toggles it ON or OFF
    }

    @Step("Check if Username field is visible")
    public boolean isUsernameVisible() {
        return isElementVisible(usernameField);

    }

    @Step("Check if Password field is visible")
    public boolean isPasswordVisible() {
        return isElementVisible(passwordField);
    }

    @Step("Check if Confirm Password field is visible")
    public boolean isConfirmPasswordVisible() {
        return isElementVisible(confirmPasswordField);
    }

    @Step("Check if 'Enabled' status radio button is visible")
    public boolean isStatusEnabledRadioVisible() {
        return isElementVisible(enabledRadioButton);
    }

    @Step("Check if 'Disabled' status radio button is visible")
    public boolean isStatusDisabledRadioVisible() {
        return isElementVisible(disabledRadioButton);
    }

    @Step("Get success toast message after assigning leave")
    public String getSuccessToastMessage() {
        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(successToastMessage));
            return toast.getText().trim();
        } catch (TimeoutException e) {
            return "No success toast appeared";
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


}
