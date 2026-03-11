package tests.regression.authentication;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


@Epic("POS System")
@Feature("Authentication")
@Owner("Salem Amortegui")

public class LoginTest extends BaseTest {



    @Test(groups = {"regression", "authentication"})
    @Story("RF-01 - As a registered user, I want to log in using my username and password so that I can access the POS system securely")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a registered user can successfully log in to the POS system using valid credentials and is redirected to the dashboard")
    public void loginWithValidCredentials() {

        DashboardPage dashboard = loginPage.login(email, password);

        assertTrue(dashboard.isLoaded(),
                "Dashboard did not load after valid login");
    }

    @Story("RF-01 - As a logged-in user, I want my session to remain active after refreshing the page so that I can continue using the POS system without being logged out")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that after a successful login, refreshing the browser page does not terminate the user session and the dashboard remains accessible")
    @Test(groups = {"regression", "authentication"})
    public void refreshingPageAfterLogin() { // RF-01.1 - Session persistence (Func. Req. to be created)

        DashboardPage dashboard = doLogin(email, password);

        assertTrue(dashboard.isLoaded());

        dashboard.refreshPage();

        assertTrue(dashboard.isLoaded(),
                "Session was lost after refresh");
    }

    @Story("RF-01 - As a logged-in user, I want to log out securely so that my session is properly terminated and unauthorized access is prevented")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can successfully log out and is redirected to the login page, ensuring the session is fully terminated")
    @Test(groups = {"regression", "authentication"})
    public void userCanLogoutSuccessfully() {

        DashboardPage dashboard = doLogin(email, password);
        assertTrue(dashboard.isLoaded());

        LoginPage loginPage = dashboard.logout();

        assertTrue(loginPage.isLoaded(),
                "User was not redirected to login after logout");

        DashboardPage dashboardAfterAccess = loginPage.tryAccessDashboard();

        assertFalse(dashboardAfterAccess.isLoaded(),
                "User accessed dashboard after logout");
    }

    @Story("RF-01 - As a logged-out user, I should not regain access to the system using the browser back button after logging out")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that after logging out, using the browser back button does not restore the previous authenticated session and access to the dashboard remains blocked")
    @Test(groups = {"regression", "authentication", "security"})
    public void backButtonDoesNotRestoreSession() {

        DashboardPage dashboard = doLogin(email, password);
        assertTrue(dashboard.isLoaded());

        LoginPage loginPage = dashboard.logout();
        assertTrue(loginPage.isLoaded());

        loginPage.goBack();
        loginPage.refreshPage();

        assertTrue(loginPage.isLoaded(),
                "Back button restored session (security issue)");
    }

    @Story("RF-01 - As a user, I should not be able to log in with invalid credentials to ensure system security")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the system prevents login when invalid username or password is entered and displays an appropriate error message without granting access")
    @Test(groups = {"regression", "authentication", "negative"})
    public void loginWithInvalidCredentials() {

        loginPage.loginWithInvalidCredentials("invalidEmail@invalid.com", "InvalidPassword123");

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message not displayed for invalid login");
    }

    @Story("RF-01 - As an unauthenticated user, I should not be able to access protected pages without logging in")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that attempting to access the dashboard URL without prior authentication redirects the user to the login page and prevents unauthorized access")
    @Test(groups = {"regression", "authentication", "security", "negative"})
    public void goToDashboardWithoutLogin() {

        DashboardPage dashboard = new DashboardPage();
        dashboard.navigateToDashboardDirectly();

        LoginPage loginPage = new LoginPage();

        assertTrue(loginPage.isLoaded(),
                "User accessed dashboard without authentication");
    }

}