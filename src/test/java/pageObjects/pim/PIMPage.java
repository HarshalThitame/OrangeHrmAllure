package pageObjects.pim;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {

    By addEmployeeButton = By.xpath("//button[contains(@class, 'oxd-button') and .//i[contains(@class, 'bi-plus')] and contains(., 'Add')]");
    By employeeListButton = By.xpath("//a[normalize-space()='Employee List']");


    public PIMPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Add button")
    public void clickAddEmployeeButton() {
        click(addEmployeeButton);
    }

    @Step("Click employee list button")
    public void clickEmployeeListButton() {
        click(employeeListButton);
    }

}
