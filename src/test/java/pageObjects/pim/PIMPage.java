package pageObjects.pim;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {

    By addEmployeeButton = By.xpath("//button[contains(@class, 'oxd-button') and .//i[contains(@class, 'bi-plus')] and contains(., 'Add')]");


    public PIMPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Add button")
    public void clickAddEmployeeButton() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
        addBtn.click();
    }

}
