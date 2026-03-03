package base;

import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;
import config.ConfigReader;

public class BaseTest {

    protected LoginPage loginPage;
    protected String email;
    protected String password;

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
    public void tearDown() {
        DriverManager.quitDriver();
    }
}