package base;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;
import config.ConfigReader;

import java.time.Duration;

public class BaseTest {

    // protected permite que las clases hijas lo usen | Esto es herencia → buena práctica
    protected WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
