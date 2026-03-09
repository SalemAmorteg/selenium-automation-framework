package tests.regression.inventory;

import base.BaseTest;
import io.qameta.allure.*;
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

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-04 - As an administrator, I want to create a new product with all required details so that it becomes available for sale in the POS system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an administrator can successfully create a new product by providing name, price, SKU, description, and category, and that the product is saved correctly in the system")
    @Owner("Salem Amortegui")
    @Test(groups = {"regression", "inventory"})
    public void adminCanCreateNewProduct() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        String productName = createTestProduct(inventory);

        Assert.assertTrue(
                inventory.isProductPresent(productName),
                "El producto no fue creado correctamente"
        );
    }

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-06 - As an administrator, I want to edit existing product information so that I can update prices, descriptions, or categories when needed")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an administrator can successfully modify the information of an existing product and that the updated data is saved and reflected correctly in the system")
    @Owner("Salem Amortegui")
    @Test(groups = {"regression", "inventory"})
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

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-06 - As an administrator, I want to delete existing products so that discontinued or incorrect items are removed from the system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an administrator can successfully delete an existing product and that the product is no longer available in the inventory list")
    @Owner("Salem Amortegui")
    @Test(groups = {"regression", "inventory"})
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



