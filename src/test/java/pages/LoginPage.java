package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    protected String BASE_URL = "https://stg.tiendo.com.co/";

    // Locators
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.tagName("button");
    private final By errorMessage = By.id("login-error");

    //Constructor que recibe el driver y el wait
    public LoginPage() {
        super();
    }

    //Metodos
    public void enterEmail(String email) {
        //wait.until: Reintenta cada 500ms buscar el elemento hasta que: El elemento aparece
        // o pasan 10 seg. | Si no aparece → lanza TimeoutException
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        driver.findElement(usernameInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void waitForRedirection() {
        wait.until(ExpectedConditions.urlContains("dashboard-tendero"));
    }

    public boolean isErrorMessageDisplayed() {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    public void navigateToTenderoDashboardUrl() {
        driver.navigate().to("https://stg.tiendo.com.co/dashboard-tendero/");
    }
    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl().equals(BASE_URL);
    }
}
