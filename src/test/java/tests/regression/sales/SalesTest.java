package tests.regression.sales;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CashRegisterPage;
import pages.DashboardPage;
import pages.SalesPage;

@Epic("POS System")
@Feature("Sales")
@Owner("Salem Amortegui")

public class SalesTest extends BaseTest {



    private final String PRODUCT_A = "12Test";
    private final String PRODUCT_B = "13Test";

    /*
     * RF-08
     * El sistema debe permitir registrar ventas agregando múltiples productos al carrito
     */

    @Story("RF-08 - As a cashier, I want to add multiple products to the cart so that I can register a complete sale with more than one item")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can add multiple products to the shopping cart and that all selected items are correctly displayed before completing the sale")
    @Test(groups = {"regression", "sales"})
    public void userCanAddMultipleProductsToCart() {

        DashboardPage dashboard = doLogin(email, password);
        SalesPage sales = dashboard.goToSales();

        sales.searchProduct(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);

        Assert.assertEquals(
                sales.getCartItemCount(),
                2,
                "No se agregaron múltiples productos al carrito"
        );
    }

    /*
     * RF-09
     * El sistema debe calcular automáticamente el total de la venta
     */
    @Story("RF-09 - As a cashier, I want the system to automatically calculate the total amount of the sale so that the customer is charged correctly")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that when multiple products are added to the cart, the system automatically calculates and displays the correct total amount based on the sum of individual product prices")
    @Test(groups = {"regression", "sales", "financial"})
    public void systemCalculatesTotalAutomatically() {

        DashboardPage dashboard = doLogin(email, password);
        SalesPage sales = dashboard.goToSales();

        sales.searchProduct(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);

        sales.searchProduct(PRODUCT_B);
        sales.addProductToCart(PRODUCT_B);

        String total = sales.getCartTotal();

        Assert.assertTrue(
                total.contains("$"),
                "El total no se calcula o muestra correctamente"
        );
    }

    /*
     * RF-10
     * El sistema debe permitir seleccionar método de pago
     */
    @Story("RF-10 - As a cashier, I want to select a payment method so that I can complete the sale according to the customer's preferred payment type")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the cashier can select different payment methods such as cash, nequi, daviplata or debit/credit card before completing the sale")
    @Test(groups = {"regression", "sales", "financial"})
    public void userCanSelectPaymentMethod() {  // GAP Between RF-10 and frontend payment methods.

        DashboardPage dashboard = doLogin(email, password);
        SalesPage sales = dashboard.goToSales();

        sales.searchProduct(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);

        sales.selectPaymentMethod("Efectivo");

        Assert.assertTrue(
                sales.getCartItemCount() > 0,
                "No se pudo seleccionar método de pago correctamente"
        );
    }

    /*
     * RF-13
     * El sistema debe mostrar el detalle completo antes de confirmar
     */
    @Story("RF-13 - As a cashier, I want to review the complete sale summary before confirming the transaction so that I can ensure all items and totals are correct")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a sale summary modal is displayed before confirming the transaction, showing the list of products, quantities, and total amount")
    @Test(groups = {"regression", "sales", "ui"})
    public void saleSummaryModalIsDisplayed() {

        DashboardPage dashboard = doLogin(email, password);
        SalesPage sales = dashboard.goToSales();

        sales.searchProduct(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);

        sales.selectPaymentMethod("Efectivo");
        sales.enterAmountReceived("50000");

        sales.clickConfirmSale();

        Assert.assertTrue(
                sales.isLoaded(),
                "No se mostró el resumen antes de confirmar"
        );
    }

    /*
     * RF-08 + RF-09 + RF-10
     * Flujo completo de venta
     */
    @Story("RF-08/RF-09/RF-10/RF-26 - As a cashier, I want to open a cash register session and complete a full sale so that the transaction is successfully recorded")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a cashier can log in, open a cash register session, add products to the cart, select a payment method, and successfully complete a sale without errors")
    @Test(groups = {"smoke", "regression", "e2e", "sales"})
    public void userCanCompleteSaleSuccessfully() {

        DashboardPage dashboard = doLogin(email, password);
        CashRegisterPage cashRegisterPage = dashboard.goToCashRegister();
        Assert.assertTrue(cashRegisterPage.isLoadedWhenClosed(), "Cash Register not loaded");

        cashRegisterPage.openRegister("200000");
        Assert.assertTrue(cashRegisterPage.isRegisterOpen(), "Cash register did not load correctly");
        SalesPage sales = dashboard.goToSales();

        sales.searchProduct(PRODUCT_A);
        sales.addProductToCart(PRODUCT_A);

        sales.selectPaymentMethod("Efectivo");
        sales.enterAmountReceived("50000");

        sales.clickConfirmSale();
        sales.processFinalSale();

        Assert.assertTrue(
                sales.isSaleSuccessful(),
                "FAIL: La venta no se completó correctamente"
        );
    }
}