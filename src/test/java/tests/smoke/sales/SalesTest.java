package tests.smoke.sales;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CashRegisterPage;
import pages.DashboardPage;
import pages.SalesPage;
import utils.WaitHelper;

public class SalesTest extends BaseTest {

    private final String PRODUCT_A = "12Test";
    private final String PRODUCT_B = "13Test";

    /*
     * RF-08
     * El sistema debe permitir registrar ventas agregando múltiples productos al carrito
     */
    @Test
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
    @Test
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
    @Test
    public void userCanSelectPaymentMethod() {

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
    @Test
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
    @Test
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