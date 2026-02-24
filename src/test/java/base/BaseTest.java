package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    // protected permite que las clases hijas lo usen | Esto es herencia → buena práctica
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String BASE_URL = "https://stg.tiendo.com.co/";
    protected DashboardPage doLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);
        loginPage.waitForRedirection();
        return new DashboardPage(driver, wait);
    }

    // Cada prueba empieza limpia | Evita dependencia entre tests
    @BeforeMethod
    public void setUp() {

        // Evita descargar manualmente el driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Explicit Wait global (Espera máximo 10 segundos hasta que se cumpla la condición)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(BASE_URL); // URL STG
    }

    // Evita consumo innecesario de memoria y Tests interdependientes
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

}
