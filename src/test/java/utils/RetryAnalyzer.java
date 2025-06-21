package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxAttempts = 2;
        if (count < maxAttempts) {
            count++;
            return true;
        }
        return false;
    }


}
