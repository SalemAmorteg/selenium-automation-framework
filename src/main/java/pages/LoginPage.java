package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import utils.WaitHelper;

public class LoginPage extends BasePage {

    // 🔹 Locators
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.tagName("button");
    private By errorMessage = By.id("login-error");

    // 🔹 Validation
    public boolean isLoaded() {
        return isVisible(loginButton);
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public DashboardPage tryAccessDashboard() {
        getDriver().navigate().to(ConfigReader.get("dashboard.url"));
        return new DashboardPage();
    }

    // 🔹 Actions
    private void enterEmail(String email) {
        type(usernameInput, email);
    }

    private void enterPassword(String password) {
        type(passwordInput, password);
    }

    private void clickLogin() {
        click(loginButton);
    }

    // 🔹 Business Action
    public DashboardPage login(String email, String password) {

        enterEmail(email);
        enterPassword(password);
        clickLogin();

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.waitUntilLoaded(); // valida que cargó

        return dashboardPage;
    }

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