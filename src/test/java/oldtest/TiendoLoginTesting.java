package oldtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TiendoLoginTesting {

    // Variables (atributos)
    WebDriver driver1;
    private static final String BASE_URL = "https://www.stg.tiendo.com.co";

    // Setup (configuración antes de tests)
    @BeforeTest
    public void firstTest() {
        driver1 = new ChromeDriver();
        navigation(BASE_URL);
    }

    // Tests (Ordenados por flujo logico)
    @Test (priority = 0)
    public void loginSuccessful() {
        passwordField();
        usernameField();
        singIn();

        }


        // Métodos auxiliares
    public void navigation(String url) {
        driver1.navigate().to(url);
    }

    public void navigateBack() {
        driver1.navigate().back();
    }

    public void navigateForward() {
        driver1.navigate().forward();
    }

    public void closeTab() {
        driver1.close();
    }

    public void quitBrowser() {
        driver1.quit();
    }

    public void windowMaximize() {
        driver1.manage().window().maximize();
    }

    public void setDimension() {
        driver1.manage().window().setSize(new Dimension(430, 932));
    }

    public void getUrl() {
        String URL = driver1.getCurrentUrl();
        System.out.println("Actual url: " + URL);
    }

    public void getTitle() {
        String TITLE = driver1.getTitle();
        System.out.println("Actual title: " + TITLE);
    }

    public void getWindowHandle() {
        String windowHandle = driver1.getWindowHandle();
        System.out.println("Window handle is: " + windowHandle);
    }

    public void usernameField() {
        driver1.findElement(By.id("username")).sendKeys("isaacamorteguitester");
    }

    public void passwordField() {
        driver1.findElement(By.id("password")).sendKeys("Testing");
    }

    public void singIn() {
        driver1.findElement(By.tagName("button")).click();
    }

    // Teardown (Limpieza despues de test)
    @AfterTest
    public void afterTest () throws InterruptedException {
        Thread.sleep(2000);
        quitBrowser();
    }


}