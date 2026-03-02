package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitHelper;

public class DashboardPage extends BasePage {

    //Locators
    private By inventoryButton = By.cssSelector("a[href='https://stg.tiendo.com.co/inventario-pos']");
    private By cashRegisterButton = By.cssSelector("a[href='https://stg.tiendo.com.co/cierre-caja-pos']");
    private By dashboardIdentifier = By.xpath("//h1[contains(text(), 'Dashboard de Tendero')]");
    private By salesButton = By.cssSelector("a[href='https://stg.tiendo.com.co/ventas-pos']");
    private By logoutButton = By.cssSelector("a.tiendo-btn.tiendo-btn-danger.tiendo-btn-block");

    //Constructor que recibe el driver y el wait
    public DashboardPage() {
       super();
    }

    //Metodos
    //Metodo que valida si el dashboard esta cargado correctamente buscando un boton unico.
    public boolean isLoaded() {
      return WaitHelper.waitForVisibility(dashboardIdentifier).isDisplayed();
    }

    public InventoryPage goToInventory() {
        WaitHelper.waitForClickable(inventoryButton).click();
        return new InventoryPage();
    }

    public SalesPage goToSales() {
        WaitHelper.waitForClickable(salesButton).click();
        return new SalesPage();
    }

    public CashRegisterPage goToCashRegister() {
        WaitHelper.waitForClickable(cashRegisterButton).click();
        return new CashRegisterPage();
    }

    public boolean currentUrlContainsDashboard() {
        return driver.getCurrentUrl().contains("dashboard");
    }

    public void clickLogout() {
        WaitHelper.waitForClickable(logoutButton).click();
    }
}