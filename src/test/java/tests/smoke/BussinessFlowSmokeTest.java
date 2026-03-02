package tests.smoke;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class BussinessFlowSmokeTest extends BaseTest {

@Test(groups = {"smoke"})
    public void fullBussinessFLowWorksCorretly() {

    // Login
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboard = doLogin("isaacamorteguitester", "Testing");
    Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load correctly");

    // Ir a Caja y abrir Caja
    CashRegisterPage cashRegisterPage = dashboard.goToCashRegister();
    Assert.assertTrue(cashRegisterPage.isLoaded(), "Cash Register not loaded");

    cashRegisterPage.openRegister("100.000,00");
    Assert.assertTrue(cashRegisterPage.isRegisterOpen(), "Cash register did not load correctly");

    // Crear producto unico
    String productName = "SmokeProduct_" + System.currentTimeMillis();
    String sku = "SKU_" + System.currentTimeMillis();

    InventoryPage inventoryPage = dashboard.goToInventory();
    Assert.assertTrue(InventoryPage.isLoaded(), "Inventory Page not loaded");
    inventoryPage.createProduct(productName, sku,"5.000,00", 10, 5);
    System.out.println("Product Created: " + productName + " " + sku);
    inventoryPage.searchProduct(productName);
    inventoryPage.waitForProductToAppear(productName);
    Assert.assertTrue(inventoryPage.isProductPresent(productName), "Product not created");


    // Registrar venta
    SalesPage salesPage = dashboard.goToSales();
    Assert.assertTrue(SalesPage.isLoaded(), "Sales Page not loaded");
    salesPage.addProductToCart(productName);
    salesPage.completeSale("Efectivo");
    Assert.assertTrue(salesPage.getSuccessMessage().contains("Venta procesada exitosamente"),
            "Sale was not processed successfully"
    );

    // Validar inventario reducido
    dashboard.goToInventory();
    Assert.assertTrue(InventoryPage.isLoaded(), "Inventory Page not loaded");
    int stock = inventoryPage.getStockForProduct(productName);
    Assert.assertEquals(stock, 9, "Inventory not reduced correctly");

    // Cerrar caja
    cashRegisterPage = dashboard.goToCashRegister();
    Assert.assertTrue(CashRegisterPage.isLoadedWhenCashIsOpen(), "Cash Register Page not loaded");
    cashRegisterPage.closeRegister("105.000,00");
    cashRegisterPage.modalCloseRegisterConfirmation();

    Assert.assertTrue(cashRegisterPage.isRegisterClosed(), "Cash register did not close");

}

}
