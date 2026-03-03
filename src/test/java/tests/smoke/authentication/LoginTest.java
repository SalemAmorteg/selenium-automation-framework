package tests.smoke.authentication;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() {

        DashboardPage dashboard = loginPage.login(email, password);

        assertTrue(dashboard.isLoaded(),
                "Dashboard did not load after valid login");
    }

    @Test
    public void refreshingPageAfterLogin() {

        DashboardPage dashboard = doLogin(email, password);

        assertTrue(dashboard.isLoaded());

        dashboard.refreshPage();

        assertTrue(dashboard.isLoaded(),
                "Session was lost after refresh");
    }

    @Test
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

    @Test
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

    @Test
    public void loginWithInvalidCredentials() {

        loginPage.loginWithInvalidCredentials("invalidEmail@invalid.com", "InvalidPassword123");

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message not displayed for invalid login");
    }

    @Test
    public void goToDashboardWithoutLogin() {

        DashboardPage dashboard = new DashboardPage();
        dashboard.navigateToDashboardDirectly();

        LoginPage loginPage = new LoginPage();

        assertTrue(loginPage.isLoaded(),
                "User accessed dashboard without authentication");
    }
}