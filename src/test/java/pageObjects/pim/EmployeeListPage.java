package pageObjects.pim;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {
    By employeeNameField = By.xpath("//label[normalize-space()='Employee Name']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
    By employeeIdField = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div/input[@class='oxd-input oxd-input--active']");
    By searchButton = By.xpath("//button[@type='submit']");
    By resetButton = By.xpath("//button[@type='reset']");
    By firstNames = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][3]/div");
    By ids = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][2]/div");
    By editButtons = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][9]/div/button[1]");
    By deleteButtons = By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][9]/div/button[2]");

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if Employee name field is visible")
    public boolean isEmployeeNameVisible() {
        return isElementVisible(employeeNameField);
    }

    @Step("Check if employee id field is visible")
    public boolean isEmployeeIdVisible() {
        return isElementVisible(employeeIdField);
    }

    @Step("Check if search button is visible")
    public boolean isSearchButtonVisible() {
        return isElementVisible(searchButton);
    }

    @Step("Check if reset button is visible")
    public boolean isResetButtonVisible() {
        return isElementVisible(resetButton);
    }

    @Step("Enter employee name")
    public void enterEmployeeName(String employeeName) {
        enterText(employeeNameField, employeeName);
        By suggestionOption = By.xpath(String.format("//*[@role='listbox']//*[contains(text(),'%s')]", employeeName));
        click(suggestionOption);

    }

    @Step("Enter employee id")
    public void enterEmployeeId(String employeeId) {
        enterText(employeeIdField, employeeId);
    }

    @Step("Click search button")
    public void clickSearchButton() {
        click(searchButton);
    }

    @Step("Click reset button")
    public void clickResetButton() {
        click(resetButton);
    }

    @Step("Click edit button")
    public void clickEditButton() {
        click(editButtons);
    }

    @Step("Click delete button")
    public void clickDeleteButton() {
        click(deleteButtons);
    }


    @Step("Get employee name")
    public String getFirstResultName() {
        return getText(firstNames);
    }

    @Step("Get employee id")
    public String getEmployeeIdFromFirstRow() {
        return getText(ids);
    }

    @Step("Get employee name from name field")
    public String getEmployeeName() {
        return getText(employeeNameField);
    }

    @Step("Get employee id from id field")
    public String getEmployeeId() {
        return getText(employeeIdField);
    }
}
