package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {


    public String tenderoDASHBOARD_URL = "https://stg.tiendo.com.co/dashboard/tendero/";

    //Locators
    private By dashboardPOSButton = By.cssSelector("a[href*='ventas-pos']");
    private By logoutButton = By.cssSelector("a.tiendo-btn.tiendo-btn-danger.tiendo-btn-block");

    //Constructor que recibe el driver y el wait
    public DashboardPage() {
       super();
    }

    //Metodos
    //Metodo que valida si el dashboard esta cargado correctamente buscando un boton unico.
    public boolean isDashboardLoaded() {

        // Esperamos hasta que el botón "Punto de Venta" sea visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPOSButton));
        return driver.findElement(dashboardPOSButton).isDisplayed();
    }

    // Metodo que valida si la url actual contiene dashboard
    public boolean currentUrlContainsDashboard() {
        return driver.getCurrentUrl().contains("dashboard");
    }
    //Metodo que da click en logoutButton
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void goToInventory() {
        driver.findElement(By.cssSelector("a[href='https://stg.tiendo.com.co/inventario-pos']")).click();
    }
}