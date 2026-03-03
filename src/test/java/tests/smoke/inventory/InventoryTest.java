package tests.smoke.inventory;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.InventoryPage;

public class InventoryTest extends BaseTest {

    // 🔹 Helper para crear producto desde InventoryPage real
    private String createTestProduct(InventoryPage inventoryPage) {

        String productName = "QA_" + System.currentTimeMillis();
        String sku = "SKU_" + System.currentTimeMillis();

        inventoryPage.createProduct(productName, sku, "50.000,00", 20, 5);

        return productName;
    }

    @Test
    public void adminCanCreateNewProduct() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        String productName = createTestProduct(inventory);

        Assert.assertTrue(
                inventory.isProductPresent(productName),
                "El producto no fue creado correctamente"
        );
    }

    @Test
    public void adminCanEditProduct() throws InterruptedException {

        DashboardPage dashboard = doLogin(email, password);
        InventoryPage inventory = dashboard.goToInventory();

        String productName = createTestProduct(inventory);

        inventory.searchProduct(productName);
        inventory.waitForProductToAppear(productName);

        inventory.clickEditButton(productName);

        String newPrice = "2.000,00";
        inventory.enterPrice(newPrice);
        inventory.clickSubmitButton();

        inventory.searchProduct(productName);
        inventory.waitForProductToAppear(productName);

        Assert.assertTrue(
                inventory.isPriceUpdated(productName, newPrice),
                "El precio no fue actualizado correctamente"
        );
    }

    @Test
    public void adminCanDeleteProduct() {

        DashboardPage dashboard = doLogin(email, password);
        InventoryPage inventory = dashboard.goToInventory();

        String productName = createTestProduct(inventory);

        Assert.assertTrue(
                inventory.isProductPresent(productName),
                "El producto no fue encontrado antes de eliminar"
        );

        inventory.deleteProduct(productName);

        Assert.assertFalse(
                inventory.isProductPresent(productName),
                "El producto no se eliminó correctamente"
        );
    }
}



