package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.DashboardPage;
import pageObjects.leave.AssignLeavePage;
import pageObjects.pim.AddEmployeePage;
import pageObjects.pim.PIMPage;

import java.time.Duration;

public class BasePage {

    protected static Logger log;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log = LoggerFactory.getLogger(this.getClass());
    }

    public DashboardPage navigateToDashBoard() {
        return new DashboardPage(driver);
    }


    public AssignLeavePage goToAssignLeavePage() {
        return new AssignLeavePage(driver);
    }

    public PIMPage goToPIMPage() {
        return new PIMPage(driver);
    }

    public AddEmployeePage goToAddEmployeePage() {
        return new AddEmployeePage(driver);
    }

}
