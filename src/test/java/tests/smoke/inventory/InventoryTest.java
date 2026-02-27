package tests.smoke.inventory;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.DashboardPage;

public class InventoryTest extends BaseTest {

    private DashboardPage dashboardPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupPages() {
        dashboardPage = new DashboardPage();
        inventoryPage = new InventoryPage();
    }
    // Método helper que crea un producto nuevo y devuelve el nombre generado.
    // Esto permite que cada test tenga su propio producto independiente.
//    private String createTestProduct() {
//
//        String productName = "QA_" + System.currentTimeMillis();
//        String sku = "SKU_" + System.currentTimeMillis();
//
 //      dashboardPage.goToInventory();
//
//        inventoryPage.clickAddProductButton();
//
//        inventoryPage.fillProductForm(
//                productName,
//                sku,
//                "10",
//                "2"
//        );
//
//        inventoryPage.enterPrice("2.000,00");
//
//        inventoryPage.clickSubmitButton();
//
//        return productName;
//    }

//    @Test
//    public void adminCanCreateNewProduct() {
//
//        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
//
//        String productName = createTestProduct();
//        System.out.println("Producto creado: " + productName);
//
//        inventoryPage.searchProduct(productName);
//        inventoryPage.waitForProductToAppear(productName);
//
//        Assert.assertTrue(
//                inventoryPage.isProductPresent(productName),
//                "El producto no fue creado correctamente"
//        );
//    }
//
//    @Test
//    public void adminCanEditProduct() {
//
//        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
//
//        String productName = createTestProduct();
//
//        System.out.println("Producto creado: " + productName);
//
//        inventoryPage.searchProduct(productName);
//
//        inventoryPage.waitForProductToAppear(productName);
//
//        inventoryPage.clickEditButton();
//
//        String newPrice = "2.000,00";
//        inventoryPage.enterPrice(newPrice);
//
//        inventoryPage.clickSubmitButton();
//
//        inventoryPage.searchProduct(productName);
//
//        Assert.assertTrue(
//                inventoryPage.isPriceUpdated(newPrice),
//                "El precio no fue actualizado correctamente"
//        );
//    }
//
//    @Test
//    public void adminCanDeleteProduct() {
//
//        DashboardPage dashboardPage = doLogin("isaacamorteguitester", "Testing");
//
//        String productName = createTestProduct();
//
//        System.out.println("Nombre del producto creado: " + productName);
//
//        inventoryPage.searchProduct(productName);
//        inventoryPage.waitForProductToAppear(productName);
//
//        Assert.assertTrue(
//                inventoryPage.isProductPresent(productName),
//                "El producto no fue encontrado antes de eliminar"
//        );
//
//        inventoryPage.deleteProduct(productName);
//        inventoryPage.waitForProductToDisappear(productName);
//
//        Assert.assertFalse(
//                inventoryPage.isProductPresent(productName),
//                "El producto no se eliminó correctamente"
//        );
//    }
}



