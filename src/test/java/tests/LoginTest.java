package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

// extends = herencia (para usar "driver")
public class LoginTest extends BaseTest {

    @Test //Happy path
    public void loginWithValidCredentials() {

        // Creamos objeto de la página Login
        // Le pasamos el driver y el wait que vienen de BaseTest
        LoginPage loginPage = new LoginPage();

        // Ejecución de Metodo
        loginPage.login("isaacamorteguitester", "Testing");

        // Esperar redirección
        loginPage.waitForRedirection();

        // Creamos objeto de la pagina Dashboard
        DashboardPage dashboardPage = new DashboardPage();

        // Validamos que el dashboard esté completamente cargado
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded(),
                "El dashboard no cargó correctamente"
        );
    }

    @Test
    public void refreshingPageAfterLogin() {
        // ----------- LOGIN ------------
        // Aqui Creamos el metodo doLogin() para hacerlo reutilizable || Encapsulamos && se puede encontrar en BaseTest
        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
        //Assert del login
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded(),
                "El dashboard no cargó correctamente"
        );
        //-------------REFRESH------------
        driver.navigate().refresh();
        //-------------ASSERT--------------
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded(),
                "La sesión se perdió despues de refrescar la pagina"
        );
        LoginPage loginPage = new LoginPage();
        Assert.assertFalse(
                loginPage.isLoginPageDisplayed(),
                "Fue redirigido al login después de hacer refresh"
        );
    }

    @Test
    public void userCanLogoutSuccessfully() {
        // ----------- LOGIN ------------
        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
        //Assert del login
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded(),
                "El dashboard no cargó correctamente"
        );
        // ----------- LOGOUT ------------
        dashboardPage.clickLogout();

        LoginPage loginPage = new LoginPage();

        //Assert
        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "No fue redirigido al login después del logout"
        );

        // -------- TRYING TO ACCESS TO DASHBOARD AFTER LOGOUT---------
        driver.get(dashboardPage.tenderoDASHBOARD_URL);
        //Assert de acceso a dashboard despues del logout
        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "El usuario pudo acceder al dashboard despues de cerrar sesión"
        );
    }

    @Test
    public void backButtonDoesNotRestoreSession() throws InterruptedException {
        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
        Assert.assertTrue(
                dashboardPage.isDashboardLoaded(),
                "EL dashboard no cargó correctamente"
        );
        dashboardPage.clickLogout();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "No fue redirigido al login después del logout"
        );
        driver.navigate().back();
        LoginPage afterBackPage = new LoginPage();
        driver.navigate().refresh();
        LoginPage afterRefreshPage = new LoginPage();
        loginPage.navigateToTenderoDashboardUrl();
        LoginPage afterGetUrl = new LoginPage();
        Assert.assertTrue(
                afterBackPage.isLoginPageDisplayed(),
                "El botón atrás restauró la sesión (problema de seguridad)"
        );
        Assert.assertFalse(
                driver.getCurrentUrl().contains("dashboard"),
                "El usuario pudo volver al dashboard usando el botón atrás - verificado por Url"
        );
    }

    @Test
    public void loginWithInvalidCredentials() {
        // Crear objeto LoginPage
        LoginPage loginPage = new LoginPage();
        // Intentar login con credenciales incorrectas
        loginPage.login("isaacamorteguitester", "WrongPassword");

        //Asserts
        // Validar que NO redirige al dashboard
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "El mensaje de error no se mostró"
        );
        //Validar URL
        Assert.assertFalse(
                driver.getCurrentUrl().contains("dashboard"),
                "Redirigió al dashboard con credenciales invalidas"
        );
    }

    @Test
    public void goToDashboardWithoutLogin() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        loginPage.navigateToTenderoDashboardUrl();

        //Asserts
        //Validar que NO regiride al dashboard
        Assert.assertFalse(
                driver.getCurrentUrl().contains("dashboard"),
                "Redirigió al dashboard sin iniciar sesion"
        );
        //Validar que continua en login page
        Assert.assertTrue(
                driver.getCurrentUrl().equals(BASE_URL), "Redirigió a otra Url sin iniciar sesion"
        );
        //Validar que NO se haya cargado el dashboard
        Assert.assertFalse(
                dashboardPage.currentUrlContainsDashboard(),
                "El dashboard se cargó sin autenticación"
        );
    }
}
