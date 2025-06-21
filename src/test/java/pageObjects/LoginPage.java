package pageObjects;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    By username = By.name("username");
    By password = By.name("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By forgotPasswordButton = By.xpath("//p[contains(normalize-space(), 'Forgot your password')]");
    By invalidCredentialMessage = By.xpath("//p[contains(normalize-space(), 'Invalid credentials')]");
    By requiredMessageForUsername = By.xpath("//div[input[@name='username']]/following-sibling::span[contains(normalize-space(), 'Required')]");
    By requiredMessageForPassword = By.xpath("//div[input[@name='password']]/following-sibling::span[contains(normalize-space(), 'Required')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username : {0}")
    public void enterUsername(String username) {
        WebElement usernameEle = wait.until(ExpectedConditions.visibilityOfElementLocated(this.username));
        usernameEle.clear();
        usernameEle.sendKeys(username);
    }

    @Step("Enter password : {0}")
    public void enterPassword(String password) {
        WebElement passEle = wait.until(ExpectedConditions.visibilityOfElementLocated(this.password));
        passEle.clear();
        passEle.sendKeys(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Login as {0}/{1}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Get invalid credential message")
    public String getInvalidCredentialMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(this.invalidCredentialMessage)).getText();
    }

    @Step("Get required message for username")
    public String getRequiredMessageForUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(this.requiredMessageForUsername)).getText();
    }

    @Step("Get required message for password")
    public String getRequiredMessageForPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(this.requiredMessageForPassword)).getText();
    }
}
