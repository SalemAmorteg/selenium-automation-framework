package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import pages.UsersPage;

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

    private By usuariosButton =
            By.cssSelector("a[href*='usuarios']");

    @Step("Navigate directly to Dashboard URL")
    public void navigateToDashboardDirectly() {
        getDriver().navigate().to(ConfigReader.get("dashboard.url"));
    }

    @Step("Wait until Dashboard page is fully loaded")
    public void waitUntilLoaded() {
        find(dashboardIdentifier);
    }

    @Step("Verify that Dashboard page is displayed")
    public boolean isLoaded() {
        try {
            return getDriver()
                    .findElement(dashboardIdentifier)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Refresh Dashboard page")
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    @Step("Logout from the system")
    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage();
    }

    @Step("Navigate to Inventory module")
    public InventoryPage goToInventory() {
        click(inventoryButton);
        InventoryPage page = new InventoryPage();
        page.waitUntilLoaded();
        return page;
    }

    @Step("Navigate to Sales module")
    public SalesPage goToSales() {
        click(salesButton);
        SalesPage page = new SalesPage();
        page.waitUntilLoaded();
        return page;
    }

    @Step("Navigate to Cas Register module")
    public CashRegisterPage goToCashRegister() {
        click(cashRegisterButton);
        CashRegisterPage page = new CashRegisterPage();
        page.waitUntilLoaded();
        return page;
    }

    @Step("Navigate to Users module")
    public UsersPage goToUsers() {
        click(usuariosButton);
        UsersPage page = new UsersPage();
        page.waitUntilLoaded();
        return page;
    }
}