package base;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    // protected permite que las clases hijas lo usen | Esto es herencia → buena práctica
    protected WebDriver driver;
    protected static final String BASE_URL = "https://stg.tiendo.com.co/";
    protected DashboardPage doLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        loginPage.waitForRedirection();
        return new DashboardPage();
    }

    @BeforeMethod
    public void setUp() {
        DriverManager.setDriver();
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
