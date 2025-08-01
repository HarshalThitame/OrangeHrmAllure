package pageObjects.pim.editEmployeeDetails;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPersonalDetailPage extends BasePage {


    //Personal details
    By employeeFirstNameField = By.xpath("//label[contains(.,'Employee Full Name')]/parent::div/following-sibling::div//input[@class='oxd-input oxd-input--active orangehrm-firstname']");
    By employeeLastNameField = By.xpath("//label[contains(.,'Employee Full Name')]/parent::div/following-sibling::div//input[@class='oxd-input oxd-input--active orangehrm-lastname']");
    By employeeMiddleNameField = By.xpath("//label[contains(.,'Employee Full Name')]/parent::div/following-sibling::div//input[@class='oxd-input oxd-input--active orangehrm-middlename']");
    By employeeIdField = By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input");
    By otherIdField = By.xpath("//label[normalize-space()='Other Id']/parent::div/following-sibling::div/input");
    By licenseNumberField = By.xpath("//label[contains(normalize-space(), 'License Number')]/parent::div/following-sibling::div//input");
    By expiryDateField = By.xpath("//label[contains(normalize-space(), 'Expiry Date')]/parent::div/following-sibling::div//input");
    By nationalityDropdownField = By.xpath("//label[contains(text(),'Nationality')]/parent::div/following-sibling::div//i");
    By maritalStatusDropdownField = By.xpath("//label[contains(text(),'Marital Status')]/parent::div/following-sibling::div//i");

    By dobInputField = By.xpath("//label[contains(normalize-space(), 'Birth')]/parent::div/following-sibling::div//input");
    By maleRadioButton = By.xpath("//label[normalize-space()='Male']");
    By femaleRadioButton = By.xpath("//label[normalize-space()='Female']");
    By savePersonalDetailsButton = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']");
    By bloodTypeDropdown = By.xpath("//label[contains(text(),'Blood')]/parent::div/following-sibling::div//i");
    //    By employeeSkillsField = By.xpath("//label[contains(normalize-space(),'Employeeskill_1753794429073')]/parent::div/following-sibling::div/input");
    By employeeTestField = By.xpath("//label[normalize-space()='Test_Field']/parent::div/following-sibling::div/input");
    By saveCustomFieldsButton = By.xpath("//div[@class='orangehrm-custom-fields']//button[@type='submit'][normalize-space()='Save']");

    //error message
    By firstNameRequiredMessage = By.xpath("//input[@name='firstName']/parent::div/following-sibling::span");
    By lastNameRequiredMessage = By.xpath("//input[@name='lastName']/parent::div/following-sibling::span");


    public EditPersonalDetailPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter first name: {firstName}")
    public void enterEmployeeFirstName(String firstName) {
        enterText(employeeFirstNameField, firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterEmployeeLastName(String lastName) {
        enterText(employeeLastNameField, lastName);
    }

    @Step("Enter middle name: {middleName}")
    public void enterEmployeeMiddleName(String middleName) {
        enterText(employeeMiddleNameField, middleName);
    }

    @Step("Enter employee ID: {empId}")
    public void enterEmployeeId(String empId) {
        enterText(employeeIdField, empId);
    }

    @Step("Enter other ID: {otherId}")
    public void enterOtherId(String otherId) {
        enterText(otherIdField, otherId);
    }

    @Step("Enter license number: {licenseNo}")
    public void enterLicenseNumber(String licenseNo) {
        enterText(licenseNumberField, licenseNo);
    }

    @Step("Enter expiry date: {date}")
    public void enterExpiryDate(String date) {
        enterText(expiryDateField, date);
    }

    @Step("Enter date of birth date: {date}")
    public void enterDOBDate(String date) {
        enterText(dobInputField, date);
    }

//    @Step("Enter employee skill: {skill}")
//    public void enterEmployeeSkill(String skill) {
//        enterText(employeeSkillsField, skill);
//    }

    @Step("Enter test field value: {testField}")
    public void enterTestField(String testField) {
        enterText(employeeTestField, testField);
    }

    @Step("Click nationality dropdown")
    public void clickNationalityDropdown() {
        click(nationalityDropdownField);
    }

    @Step("Click marital status dropdown")
    public void clickMaritalStatusDropdown() {
        click(maritalStatusDropdownField);
    }


    @Step("Click blood type dropdown")
    public void clickBloodTypeDropdown() {
        click(bloodTypeDropdown);
    }

    @Step("Select 'Male' gender")
    public void selectMaleGender() {
        click(maleRadioButton);
    }

    @Step("Select 'Female' gender")
    public void selectFemaleGender() {
        click(femaleRadioButton);
    }

    @Step("Click 'Save Personal Details' button")
    public void clickSavePersonalDetails() {
        click(savePersonalDetailsButton);
    }

    @Step("Click 'Save Custom Fields' button")
    public void clickSaveCustomFields() {
        click(saveCustomFieldsButton);
    }

    @Step("Check if 'First Name' field is visible")
    public boolean isEmployeeFirstNameFieldVisible() {
        return isElementVisible(employeeFirstNameField);
    }

    @Step("Check if 'Last Name' field is visible")
    public boolean isEmployeeLastNameFieldVisible() {
        return isElementVisible(employeeLastNameField);
    }

    @Step("Check if 'Middle Name' field is visible")
    public boolean isEmployeeMiddleNameFieldVisible() {
        return isElementVisible(employeeMiddleNameField);
    }

    @Step("Check if 'Employee ID' field is visible")
    public boolean isEmployeeIdFieldVisible() {
        return isElementVisible(employeeIdField);
    }

    @Step("Check if 'Other ID' field is visible")
    public boolean isOtherIdFieldVisible() {
        return isElementVisible(otherIdField);
    }

    @Step("Check if 'License Number' field is visible")
    public boolean isLicenseNumberFieldVisible() {
        return isElementVisible(licenseNumberField);
    }

    @Step("Check if 'Expiry Date' field is visible")
    public boolean isExpiryDateFieldVisible() {
        return isElementVisible(expiryDateField);
    }

    @Step("Check if 'Nationality' dropdown is visible")
    public boolean isNationalityDropdownVisible() {
        return isElementVisible(nationalityDropdownField);
    }

    @Step("Check if 'Marital Status' dropdown is visible")
    public boolean isMaritalStatusDropdownVisible() {
        return isElementVisible(maritalStatusDropdownField);
    }

    @Step("Check if 'Date of Birth' dropdown is visible")
    public boolean isDobDropdownVisible() {
        return isElementVisible(dobInputField);
    }

    @Step("Check if 'Male' radio button is visible")
    public boolean isMaleRadioButtonVisible() {
        return isElementVisible(maleRadioButton);
    }

    @Step("Check if 'Female' radio button is visible")
    public boolean isFemaleRadioButtonVisible() {
        return isElementVisible(femaleRadioButton);
    }

    @Step("Check if 'Save Personal Details' button is visible")
    public boolean isSavePersonalDetailsButtonVisible() {
        return isElementVisible(savePersonalDetailsButton);
    }

    @Step("Check if 'Blood Type' dropdown is visible")
    public boolean isBloodTypeDropdownVisible() {
        return isElementVisible(bloodTypeDropdown);
    }
//
//    @Step("Check if 'Employee Skill' field is visible")
//    public boolean isEmployeeSkillsFieldVisible() {
//        return isElementVisible(employeeSkillsField);
//    }

    @Step("Check if 'Test Field' is visible")
    public boolean isEmployeeTestFieldVisible() {
        return isElementVisible(employeeTestField);
    }

    @Step("Check if 'Save Custom Fields' button is visible")
    public boolean isSaveCustomFieldsButtonVisible() {
        return isElementVisible(saveCustomFieldsButton);
    }

    //getError messages
    @Step("Get error message if first name is empty")
    public String getErrorMessageFirstName() {
        return getText(firstNameRequiredMessage);
    }

    @Step("Get error message if last name is empty")
    public String getErrorMessageLastName() {
        return getText(lastNameRequiredMessage);
    }


    @Step("Clear First name text field")
    public void clearFirstNameField() {
        clearField(employeeFirstNameField);
    }

    @Step("Clear id text field")
    public void clearEmployeeIdField() {
        clearField(employeeIdField);
    }

    @Step("Clear last name text field")
    public void clearLastNameField() {
        clearField(employeeLastNameField);
    }

    @Step("Select nationality : {indian}")
    public void selectNationality(String name) {
        click(nationalityDropdownField);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", name));
        click(suggestionOption);
    }

    @Step("Select marital status : {status}")
    public void selectMaritalStatus(String status) {
        click(maritalStatusDropdownField);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", status));
        click(suggestionOption);
    }

    @Step("Select blood type : {type}")
    public void selectBloodType(String type) {
        click(bloodTypeDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", type));
        click(suggestionOption);
    }

    @Step("Check is male selected")
    public boolean isMaleGenderSelected() {
        By male = By.xpath("//label[normalize-space()='Male']/span");
        String maleClass = driver.findElement(male).getAttribute("class");
        assert maleClass != null;
        return maleClass.contains("oxd-radio-input--focus");
    }

    @Step("Check is female selected")
    public boolean isFemaleGenderSelected() {
        By male = By.xpath("//label[normalize-space()='Female']/span");
        String maleClass = driver.findElement(male).getAttribute("class");
        assert maleClass != null;
        return maleClass.contains("oxd-radio-input--focus");
    }
}
