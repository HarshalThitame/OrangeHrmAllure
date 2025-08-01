package pageObjects.pim.editEmployeeDetails;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditContactDetailsPage extends BasePage {


    By street1TextField = By.xpath("//label[normalize-space()='Street 1']/parent::div/following-sibling::div/input");
    By street2TextField = By.xpath("//label[normalize-space()='Street 2']/parent::div/following-sibling::div/input");
    By cityTextField = By.xpath("//label[normalize-space()='City']/parent::div/following-sibling::div/input");
    By stateProvinceTextField = By.xpath("//label[normalize-space()='State/Province']/parent::div/following-sibling::div/input");
    By zipPostalCodeTextField = By.xpath("//label[normalize-space()='Zip/Postal Code']/parent::div/following-sibling::div/input");
    By homeTelephoneTextField = By.xpath("//label[normalize-space()='Home']/parent::div/following-sibling::div/input");
    By mobileTelephoneTextField = By.xpath("//label[normalize-space()='Mobile']/parent::div/following-sibling::div/input");
    By workTelephoneTextField = By.xpath("//label[normalize-space()='Work']/parent::div/following-sibling::div/input");

    By workEmailTextField = By.xpath("//label[normalize-space()='Work Email']/parent::div/following-sibling::div/input");
    By otherEmailTextField = By.xpath("//label[normalize-space()='Other Email']/parent::div/following-sibling::div/input");

    By countryDropdown = By.xpath("//label[normalize-space()='Country']/parent::div/following-sibling::div//i");

    By saveButton = By.xpath("//button[@type='submit']");

    //email error
    By workEmailErrorMessage = By.xpath("//label[contains(.,'Work Email')]/parent::div/following-sibling::span");
    By otherEmailErrorMessage = By.xpath("//label[contains(.,'Other Email')]/parent::div/following-sibling::span");
    By homeTelephoneErrorMessage = By.xpath("//label[contains(.,'Home')]/parent::div/following-sibling::span");
    By workTelephoneErrorMessage = By.xpath("//label[contains(.,'Work')]/parent::div/following-sibling::span");
    By mobileTelephoneErrorMessage = By.xpath("//label[contains(.,'Mobile')]/parent::div/following-sibling::span");
    By street1ExceedErrorMessage = By.xpath("//label[contains(.,'Street 1')]/parent::div/following-sibling::span");
    By street2ExceedErrorMessage = By.xpath("//label[contains(.,'Street 2')]/parent::div/following-sibling::span");
    By cityExceedErrorMessage = By.xpath("//label[contains(.,'City')]/parent::div/following-sibling::span");
    By stateExceedErrorMessage = By.xpath("//label[contains(.,'State/Province')]/parent::div/following-sibling::span");
    By zipCodeExceedErrorMessage = By.xpath("//label[contains(.,'Zip/Postal Code')]/parent::div/following-sibling::span");


    public EditContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if 'Street 1' field is visible")
    public boolean isStreet1FieldVisible() {
        return isElementVisible(street1TextField);
    }

    @Step("Check if 'Street 2' field is visible")
    public boolean isStreet2FieldVisible() {
        return isElementVisible(street2TextField);
    }

    @Step("Check if 'City' field is visible")
    public boolean isCityFieldVisible() {
        return isElementVisible(cityTextField);
    }

    @Step("Check if 'State/Province' field is visible")
    public boolean isStateFieldVisible() {
        return isElementVisible(stateProvinceTextField);
    }

    @Step("Check if 'Zip/Postal Code' field is visible")
    public boolean isZipCodeFieldVisible() {
        return isElementVisible(zipPostalCodeTextField);
    }

    @Step("Check if 'Country' dropdown is visible")
    public boolean isCountryDropdownVisible() {
        return isElementVisible(countryDropdown);
    }

    @Step("Check if 'Home Phone' field is visible")
    public boolean isHomePhoneFieldVisible() {
        return isElementVisible(homeTelephoneTextField);
    }

    @Step("Check if 'Mobile' field is visible")
    public boolean isMobileFieldVisible() {
        return isElementVisible(mobileTelephoneTextField);
    }

    @Step("Check if 'Work Phone' field is visible")
    public boolean isWorkPhoneFieldVisible() {
        return isElementVisible(workTelephoneTextField);
    }

    @Step("Check if 'Work Email' field is visible")
    public boolean isWorkEmailFieldVisible() {
        return isElementVisible(workEmailTextField);
    }

    @Step("Check if 'Other Email' field is visible")
    public boolean isOtherEmailFieldVisible() {
        return isElementVisible(otherEmailTextField);
    }

    @Step("Check if 'Save' button is visible")
    public boolean isSaveButtonVisible() {
        return isElementVisible(saveButton);
    }

    @Step("Enter Street 1: {street1}")
    public void enterStreet1(String street1) {
        enterText(street1TextField, street1);
    }

    @Step("Enter Street 2: {street2}")
    public void enterStreet2(String street2) {
        enterText(street2TextField, street2);
    }

    @Step("Enter City: {city}")
    public void enterCity(String city) {
        enterText(cityTextField, city);
    }

    @Step("Enter State/Province: {state}")
    public void enterState(String state) {
        enterText(stateProvinceTextField, state);
    }

    @Step("Enter Zip/Postal Code: {zip}")
    public void enterZipCode(String zip) {
        enterText(zipPostalCodeTextField, zip);
    }

    @Step("Select Country: {country}")
    public void selectCountry(String country) {
        click(countryDropdown);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", country));
        click(suggestionOption);
    }

    @Step("Enter Home Phone: {home}")
    public void enterHomePhone(String home) {
        enterText(homeTelephoneTextField, home);
    }

    @Step("Enter Mobile: {mobile}")
    public void enterMobile(String mobile) {
        enterText(mobileTelephoneTextField, mobile);
    }

    @Step("Enter Work Phone: {work}")
    public void enterWorkPhone(String work) {
        enterText(workTelephoneTextField, work);
    }

    @Step("Enter Work Email: {email}")
    public void enterWorkEmail(String email) {
        enterText(workEmailTextField, email);
    }

    @Step("Enter Other Email: {email}")
    public void enterOtherEmail(String email) {
        enterText(otherEmailTextField, email);
    }

    @Step("Click Save button")
    public void clickSaveButton() {
        click(saveButton);
    }

    @Step("Get work email error message")
    public String getWorkEmailErrorMessage() {
        return getText(workEmailErrorMessage);
    }

    @Step("Get other email error message")
    public String getOtherEmailErrorMessage() {
        return getText(otherEmailErrorMessage);
    }

    @Step("Get mobile telephone error message")
    public String getMobileTelephoneErrorMessage() {
        return getText(mobileTelephoneErrorMessage);
    }

    @Step("Get work telephone error message")
    public String getWorkTelephoneErrorMessage() {
        return getText(workTelephoneErrorMessage);
    }

    @Step("Get home telephone error message")
    public String getHomeTelephoneErrorMessage() {
        return getText(homeTelephoneErrorMessage);
    }

    @Step("Get Street1 error message")
    public String getStreet1ErrorMessage() {
        return getText(street1ExceedErrorMessage);
    }

    @Step("Get Street2 error message")
    public String getStreet2ErrorMessage() {
        return getText(street2ExceedErrorMessage);
    }

    @Step("Get city error message")
    public String getCityErrorMessage() {
        return getText(cityExceedErrorMessage);
    }

    @Step("Get state error message")
    public String getStateErrorMessage() {
        return getText(stateExceedErrorMessage);
    }

    @Step("Get zip code error message")
    public String getZipCodeErrorMessage() {
        return getText(zipCodeExceedErrorMessage);
    }
}
