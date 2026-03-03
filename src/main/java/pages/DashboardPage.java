package pages;

import base.BasePage;
import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitHelper;

import java.sql.Driver;

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
    public void navigateToDashboardDirectly() {
        getDriver().navigate().to(ConfigReader.get("dashboard.url"));
    }

    public boolean isLoaded() {
        try {
            return getDriver()
                    .findElement(dashboardIdentifier)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilLoaded() {
        WaitHelper.waitForVisibility(dashboardIdentifier);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage();
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
       return DriverManager.getDriver().getCurrentUrl().contains("dashboard");
    }

    public void clickLogout() {
        WaitHelper.waitForClickable(logoutButton).click();
    }
}