package utils;

import factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        if (groups != null) {
            for (String group : groups) {
                Allure.label("tag", group);
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        takeScreenshot();
        Allure.getLifecycle().addAttachment("Failure Screenshot", "image/png", "png", takeScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.addAttachment("Skipped reason", result.getThrowable().getMessage());
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] takeScreenshot() {
        try {
            return ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("❌ Screenshot capture failed: " + e.getMessage());
            return new byte[0];
        }
    }
}
