package pageObjects.pim.editEmployeeDetails;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditEmployeePage extends BasePage {

    By personalDetailsButton = By.xpath("//a[contains(.,'Personal Details')]");
    By contactDetailsButton = By.xpath("//a[contains(.,'Contact Details')]");
    By emergencyContactsButton = By.xpath("//a[contains(.,'Emergency Contacts')]");
    By dependentsButton = By.xpath("//a[contains(.,'Dependents')]");
    By immigrationButton = By.xpath("//a[contains(.,'Immigration')]");
    By jobButton = By.xpath("//a[contains(.,'Job')]");
    By salaryButton = By.xpath("//a[contains(.,'Salary')]");
    By reportToButton = By.xpath("//a[contains(.,'Report-to')]");
    By qualificationButtons = By.xpath("//a[contains(.,'Qualifications')]");
    By membershipsButtons = By.xpath("//a[contains(.,'Memberships')]");


    public EditEmployeePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if 'Personal Details' tab is visible")
    public boolean isPersonalDetailsTabVisible() {
        return isElementVisible(personalDetailsButton);
    }

    @Step("Check if 'Contact Details' tab is visible")
    public boolean isContactDetailsTabVisible() {
        return isElementVisible(contactDetailsButton);
    }

    @Step("Check if 'Emergency Contacts' tab is visible")
    public boolean isEmergencyContactsTabVisible() {
        return isElementVisible(emergencyContactsButton);
    }

    @Step("Check if 'Dependents' tab is visible")
    public boolean isDependentsTabVisible() {
        return isElementVisible(dependentsButton);
    }

    @Step("Check if 'Immigration' tab is visible")
    public boolean isImmigrationTabVisible() {
        return isElementVisible(immigrationButton);
    }

    @Step("Check if 'Job' tab is visible")
    public boolean isJobTabVisible() {
        return isElementVisible(jobButton);
    }

    @Step("Check if 'Salary' tab is visible")
    public boolean isSalaryTabVisible() {
        return isElementVisible(salaryButton);
    }

    @Step("Check if 'Report-To' tab is visible")
    public boolean isReportToTabVisible() {
        return isElementVisible(reportToButton);
    }

    @Step("Check if 'Qualifications' tab is visible")
    public boolean isQualificationsTabVisible() {
        return isElementVisible(qualificationButtons);
    }

    @Step("Check if 'Memberships' tab is visible")
    public boolean isMembershipsTabVisible() {
        return isElementVisible(membershipsButtons);
    }

    @Step("Click on 'Personal Details' tab")
    public void clickPersonalDetailsTab() {
        click(personalDetailsButton);
    }

    @Step("Click on 'Contact Details' tab")
    public void clickContactDetailsTab() {
        click(contactDetailsButton);
    }

    @Step("Click on 'Emergency Contacts' tab")
    public void clickEmergencyContactsTab() {
        click(emergencyContactsButton);
    }

    @Step("Click on 'Dependents' tab")
    public void clickDependentsTab() {
        click(dependentsButton);
    }

    @Step("Click on 'Immigration' tab")
    public void clickImmigrationTab() {
        click(immigrationButton);
    }

    @Step("Click on 'Job' tab")
    public void clickJobTab() {
        click(jobButton);
    }

    @Step("Click on 'Salary' tab")
    public void clickSalaryTab() {
        click(salaryButton);
    }

    @Step("Click on 'Report-To' tab")
    public void clickReportToTab() {
        click(reportToButton);
    }

    @Step("Click on 'Qualifications' tab")
    public void clickQualificationsTab() {
        click(qualificationButtons);
    }

    @Step("Click on 'Memberships' tab")
    public void clickMembershipsTab() {
        click(membershipsButtons);
    }

}
