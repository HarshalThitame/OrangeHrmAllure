package pageObjects.pim.editEmployeeDetails;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditSalaryDetailsPage extends BasePage {

    By salaryComponentTextField = By.xpath("//label[contains(.,'Salary Component')]/parent::div/following-sibling::div/input");
    By amountTextField1 = By.xpath("(//label[contains(.,'Amount')]/parent::div/following-sibling::div/input)[1]");
    By commentTextArea = By.xpath("//label[contains(.,'Comments')]/parent::div/following-sibling::div/textarea");
    By directDepositOnOffToggleButton = By.xpath("//p[contains(.,'Direct Deposit')]/following-sibling::div//span");
    By accountNumberTextField = By.xpath("//label[contains(.,'Account Number')]/parent::div/following-sibling::div/input");
    By routingNumberTextField = By.xpath("//label[contains(.,'Routing Number')]/parent::div/following-sibling::div/input");
    By amountTextField2 = By.xpath("(//label[contains(.,'Amount')]/parent::div/following-sibling::div/input)[2]");

    By payGradeDropdown = By.xpath("//label[contains(.,'Pay Grade')]/parent::div/following-sibling::div//i");
    By payFrequencyDropdown = By.xpath("//label[contains(.,'Pay Frequency')]/parent::div/following-sibling::div//i");
    By currencyDropdown = By.xpath("//label[contains(.,'Currency')]/parent::div/following-sibling::div//i");
    By accountTypeDropdown = By.xpath("//label[contains(.,'Account Type')]/parent::div/following-sibling::div//i");

    By addSalaryComponentButton = By.xpath("//h6[contains(.,'Salary Components')]/following-sibling::button");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By saveButton = By.xpath("//button[@type='submit']");


    //error message
    By salaryComponentErrorMessage = By.xpath("//label[contains(.,'Salary Component')]/parent::div/following-sibling::span");
    By currencyErrorMessage = By.xpath("//label[contains(.,'Currency')]/parent::div/following-sibling::span");
    By amount1ErrorMessage = By.xpath("(//label[contains(.,'Amount')]/parent::div/following-sibling::span)[1]");
    By commentErrorMessage = By.xpath("//label[contains(.,'Comments')]/parent::div/following-sibling::span");
    By accountNumberErrorMessage = By.xpath("//label[contains(.,'Account Number')]/parent::div/following-sibling::span");
    By routingNumberErrorMessage = By.xpath("//label[contains(.,'Routing Number')]/parent::div/following-sibling::span");
    By amount2ErrorMessage = By.xpath("(//label[contains(.,'Amount')]/parent::div/following-sibling::span)[2]");

    public EditSalaryDetailsPage(WebDriver driver) {
        super(driver);
    }

    //check visible

    @Step("Check if 'Salary Component' text field is visible")
    public boolean isSalaryComponentTextFieldVisible() {
        return isElementVisible(salaryComponentTextField);
    }

    @Step("Check if 'Amount 1' text field is visible")
    public boolean isAmountTextField1Visible() {
        return isElementVisible(amountTextField1);
    }

    @Step("Check if 'Comments' text area is visible")
    public boolean isCommentTextAreaVisible() {
        return isElementVisible(commentTextArea);
    }

    @Step("Check if 'Direct Deposit' toggle button is visible")
    public boolean isDirectDepositToggleVisible() {
        return isElementVisible(directDepositOnOffToggleButton);
    }

    @Step("Check if 'Account Number' text field is visible")
    public boolean isAccountNumberTextFieldVisible() {
        return isElementVisible(accountNumberTextField);
    }

    @Step("Check if 'Routing Number' text field is visible")
    public boolean isRoutingNumberTextFieldVisible() {
        return isElementVisible(routingNumberTextField);
    }

    @Step("Check if 'Amount 2' text field is visible")
    public boolean isAmountTextField2Visible() {
        return isElementVisible(amountTextField2);
    }

    @Step("Check if 'Pay Grade' dropdown is visible")
    public boolean isPayGradeDropdownVisible() {
        return isElementVisible(payGradeDropdown);
    }

    @Step("Check if 'Pay Frequency' dropdown is visible")
    public boolean isPayFrequencyDropdownVisible() {
        return isElementVisible(payFrequencyDropdown);
    }

    @Step("Check if 'Currency' dropdown is visible")
    public boolean isCurrencyDropdownVisible() {
        return isElementVisible(currencyDropdown);
    }

    @Step("Check if 'Account Type' dropdown is visible")
    public boolean isAccountTypeDropdownVisible() {
        return isElementVisible(accountTypeDropdown);
    }

    @Step("Check if 'Cancel' button is visible")
    public boolean isCancelButtonVisible() {
        return isElementVisible(cancelButton);
    }

    @Step("Check if 'Save' button is visible")
    public boolean isSaveButtonVisible() {
        return isElementVisible(saveButton);
    }

    @Step("Check if Amount error is visible")
    public boolean isAmount1ErrorMessageVisible() {
        return isElementVisible(amount1ErrorMessage);
    }

    @Step("Enter Salary Component: {salaryComponent}")
    public void enterSalaryComponent(String salaryComponent) {
        enterText(salaryComponentTextField, salaryComponent);
    }

    @Step("Enter Amount 1: {amount}")
    public void enterAmount1(String amount) {
        enterText(amountTextField1, amount);
    }

    @Step("Enter Comment: {comment}")
    public void enterComment(String comment) {
        enterText(commentTextArea, comment);
    }

    @Step("Enter Account Number: {accountNumber}")
    public void enterAccountNumber(String accountNumber) {
        enterText(accountNumberTextField, accountNumber);
    }

    @Step("Enter Routing Number: {routingNumber}")
    public void enterRoutingNumber(String routingNumber) {
        enterText(routingNumberTextField, routingNumber);
    }

    @Step("Enter Amount 2: {amount}")
    public void enterAmount2(String amount) {
        enterText(amountTextField2, amount);
    }

    @Step("Select pay grade")
    public void selectPayGrade(String payGrade) {
        click(payGradeDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", payGrade));
        click(suggestionOption);
    }

    @Step("Select pay frequency: {payFrequency}")
    public void selectPayFrequency(String payFrequency) {
        click(payFrequencyDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", payFrequency));
        click(suggestionOption);
    }

    @Step("Select currency: {currency}")
    public void selectCurrency(String currency) {
        click(currencyDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", currency));
        click(suggestionOption);
    }

    @Step("Select account type: {accountType}")
    public void selectAccountType(String accountType) {
        click(accountTypeDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", accountType));
        click(suggestionOption);
    }

    @Step("Click 'Direct Deposit' toggle button")
    public void clickDirectDepositToggle() {
        click(directDepositOnOffToggleButton);
    }

    @Step("Click 'Cancel' button")
    public void clickCancelButton() {
        click(cancelButton);
    }

    @Step("Click 'Save' button")
    public void clickSaveButton() {
        click(saveButton);
    }

    @Step("Click add salary component button")
    public void clickAddSalaryComponentButton() {
        click(addSalaryComponentButton);
    }

    @Step("Get 'Salary Component' error message")
    public String getSalaryComponentErrorMessage() {
        return getText(salaryComponentErrorMessage);
    }

    @Step("Get 'Amount 1' error message")
    public String getAmount1ErrorMessage() {
        return getText(amount1ErrorMessage);
    }

    @Step("Get 'Comments' error message")
    public String getCommentErrorMessage() {
        return getText(commentErrorMessage);
    }

    @Step("Get 'Account Number' error message")
    public String getAccountNumberErrorMessage() {
        return getText(accountNumberErrorMessage);
    }

    @Step("Get 'Routing Number' error message")
    public String getRoutingNumberErrorMessage() {
        return getText(routingNumberErrorMessage);
    }

    @Step("Get 'Amount 2' error message")
    public String getAmount2ErrorMessage() {
        return getText(amount2ErrorMessage);
    }

    @Step("Get 'Currency' error message")
    public String getCurrencyErrorMessage() {
        return getText(currencyErrorMessage);
    }

    @Step("Get value of field: {field}")
    public String getValueOfField(String field) {
        By locator = switch (field) {
            case "salaryComponent" -> salaryComponentTextField;
            case "amount1" -> amountTextField1;
            case "comment" -> commentTextArea;
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        };
        return getText(locator);
    }


}
