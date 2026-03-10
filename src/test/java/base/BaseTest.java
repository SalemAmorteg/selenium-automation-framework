package base;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.DashboardPage;
import pages.LoginPage;
import config.ConfigReader;
import utils.AllureEnvironmentWriter;
import org.testng.ITestResult;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;


public class BaseTest {

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected LoginPage loginPage;
    protected String email;
    protected String password;

    @BeforeSuite
    public void setupEnvironment() {
        AllureEnvironmentWriter.writeEnvironmentInfo();
    }

    @BeforeMethod
    public void setUp() {

        DriverManager.setDriver();

        DriverManager.getDriver().manage().window().maximize();

        DriverManager.getDriver()
                .get(ConfigReader.get("base.url"));

        loginPage = new LoginPage();

        email = ConfigReader.get("username");
        password = ConfigReader.get("password");
    }

    protected DashboardPage doLogin(String username, String password) {
        return loginPage.login(username, password);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot for failed and successful tests to include in Allure reports
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SUCCESS) {
            try {
                TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                String status = result.getStatus() == ITestResult.FAILURE ? "Failure" : "Success";
                Allure.addAttachment("Screenshot on " + status, "image/png", new ByteArrayInputStream(screenshot), ".png");
            } catch (Exception e) {
                // Log or handle screenshot capture failure if needed
            }
        }
        DriverManager.quitDriver();
    }
}