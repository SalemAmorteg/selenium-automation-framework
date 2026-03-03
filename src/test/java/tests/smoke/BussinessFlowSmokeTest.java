package tests.smoke;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class BussinessFlowSmokeTest extends BaseTest {

    private InventoryPage inventoryPage;

    @Test(groups = {"smoke"})
    public void fullBussinessFLowWorksCorretly() {

    // Login
    DashboardPage dashboard = doLogin(email, password);
    Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load correctly");

    // Ir a Caja y abrir Caja
    CashRegisterPage cashRegisterPage = dashboard.goToCashRegister();
    Assert.assertTrue(cashRegisterPage.isLoadedWhenClosed(), "Cash Register not loaded or is opened");

    cashRegisterPage.openRegister("200000");
    Assert.assertTrue(cashRegisterPage.isRegisterOpen(), "Cash register did not load correctly");

    // Crear producto unico
    String productName = "SmokeProduct_" + System.currentTimeMillis();
    String sku = "SKU_" + System.currentTimeMillis();

    InventoryPage inventoryPage = dashboard.goToInventory();
    Assert.assertTrue(inventoryPage.isLoaded(), "Inventory Page not loaded");
    inventoryPage.createProduct(productName, sku,"5000", 10, 5);
    inventoryPage.searchProduct(productName);
    inventoryPage.waitForProductToAppear(productName);
    Assert.assertTrue(inventoryPage.isProductPresent(productName), "Product not created");


    // Registrar venta
    SalesPage salesPage = dashboard.goToSales();
    Assert.assertTrue(salesPage.isLoaded(), "Sales Page not loaded");
    salesPage.searchProduct(productName);
    salesPage.addProductToCart(productName);
    salesPage.selectPaymentMethod("Efectivo");
    salesPage.enterAmountReceived("5000");
    salesPage.clickConfirmSale();
    salesPage.processFinalSale();

    Assert.assertTrue(salesPage.isSaleSuccessful(),
            "Sale was not processed successfully"
    );

    // Validar inventario reducido
    inventoryPage = dashboard.goToInventory();
    Assert.assertTrue(inventoryPage.isLoaded(), "Inventory Page not loaded");
    int stock = inventoryPage.getStockForProduct(productName);
    Assert.assertEquals(stock, 9, "Inventory not reduced correctly");

    // Cerrar caja
    cashRegisterPage = dashboard.goToCashRegister();
    Assert.assertTrue(cashRegisterPage.isLoadedWhenOpen(), "Cash Register Page not loaded");
    cashRegisterPage.closeRegister("205000");
    cashRegisterPage.confirmCloseRegisterModal();

    Assert.assertTrue(cashRegisterPage.isRegisterClosed(), "Cash register did not close");

}

}
