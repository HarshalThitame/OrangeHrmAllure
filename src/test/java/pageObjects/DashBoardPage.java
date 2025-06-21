package pageObjects;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashBoardPage extends BasePage {

    By validationText = By.xpath("//h6[normalize-space()='Dashboard']");
    By userProfileDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    By aboutButton = By.xpath("//a[normalize-space()='About']");
    By supportButton = By.xpath("//a[normalize-space()='Support']");
    By changePasswordButton = By.xpath("//a[normalize-space()='Change Password']");
    By loginButton = By.xpath("//a[normalize-space()='Logout']");


    public DashBoardPage(WebDriver driver) {
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
}
