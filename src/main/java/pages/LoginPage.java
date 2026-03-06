package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    // 🔹 Locators
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.tagName("button");
    private By errorMessage = By.id("login-error");

    // 🔹 Validation
    @Step("Validate that Login page is loaded")
    public boolean isLoaded() {
        return isVisible(loginButton);
    }

    @Step("Navigate back using browser back button")
    public void goBack() {
        getDriver().navigate().back();
    }

    @Step("Refresh the current page")
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    @Step("Attempt to access Dashboard directly without authentication")
    public DashboardPage tryAccessDashboard() {
        getDriver().navigate().to(ConfigReader.get("dashboard.url"));
        return new DashboardPage();
    }

    // 🔹 Private Actions
    @Step("Enter email: {email}")
    private void enterEmail(String email) {
        type(usernameInput, email);
    }

    @Step("Enter password")
    private void enterPassword(String password) {
        type(passwordInput, password);
    }

    @Step("Click Login button")
    private void clickLogin() {
        click(loginButton);
    }

    // 🔹 Business Action
    @Step("Login with valid credentials")
    public DashboardPage login(String email, String password) {

        enterEmail(email);
        enterPassword(password);
        clickLogin();

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.waitUntilLoaded(); // valida que cargó

        return dashboardPage;
    }

    @Step("Attempt login with invalid credentials")
    public void loginWithInvalidCredentials(String email, String password) {

        type(usernameInput, email);
        type(passwordInput, password);
        click(loginButton);
    }

    // 🔹 Validation
    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessage);
    }
}