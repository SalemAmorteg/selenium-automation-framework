package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureScreenshot;
import driver.DriverManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        DriverManager testClass = (DriverManager) result.getInstance();
        AllureScreenshot.captureScreenshot(testClass.getDriver());

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        DriverManager testClass = (DriverManager) result.getInstance();
        AllureScreenshot.captureFinalScreenshot(testClass.getDriver());
    }
}