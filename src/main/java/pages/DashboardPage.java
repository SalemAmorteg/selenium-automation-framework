package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    // 🔹 Locators (sin URL hardcodeada)
    private By dashboardIdentifier =
            By.xpath("//h1[contains(text(), 'Dashboard de Tendero')]");

    private By inventoryButton =
            By.cssSelector("a[href*='inventario-pos']");

    private By salesButton =
            By.cssSelector("a[href*='ventas-pos']");

    private By cashRegisterButton =
            By.cssSelector("a[href*='cierre-caja-pos']");

    private By logoutButton =
            By.cssSelector("a.tiendo-btn-danger");

    // 🔹 Navigation
    public void navigateToDashboardDirectly() {
        getDriver().navigate().to(ConfigReader.get("dashboard.url"));
    }

    // 🔹 Synchronization
    public void waitUntilLoaded() {
        find(dashboardIdentifier);
    }

    // 🔹 Validation
    public boolean isLoaded() {
        try {
            return getDriver()
                    .findElement(dashboardIdentifier)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Actions
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage();
    }

    // 🔹 Module Navigation
    public InventoryPage goToInventory() {
        click(inventoryButton);
        InventoryPage page = new InventoryPage();
        page.waitUntilLoaded();
        return page;
    }

    public SalesPage goToSales() {
        click(salesButton);
        SalesPage page = new SalesPage();
        page.waitUntilLoaded();
        return page;
    }

    public CashRegisterPage goToCashRegister() {
        click(cashRegisterButton);
        CashRegisterPage page = new CashRegisterPage();
        page.waitUntilLoaded();
        return page;
    }
}