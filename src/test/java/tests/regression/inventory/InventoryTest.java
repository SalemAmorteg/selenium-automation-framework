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
    public void adminCanEditProduct() {

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

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-04 - As an administrator, I want to create a new product with all required details so that it becomes available for sale in the POS system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the description field is present in the product creation form as a required field")
    @Owner("Salem Amortegui")
    @Issue("https://github.com/SalemAmorteg/selenium-automation-framework/issues/6")
    @Test(groups = {"regression", "inventory"})
    public void descriptionFieldIsPresentInProductCreationForm() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        inventory.openCreateProductForm();

        Assert.assertTrue(
                inventory.isDescriptionFieldPresent(),
                "El campo de descripción no está presente en el formulario de creación de producto"
        );
    }

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-04 - As an administrator, I want to create a new product with all required details so that it becomes available for sale in the POS system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the category field is present in the product creation form as a required field")
    @Owner("Salem Amortegui")
    @Issue("https://github.com/SalemAmorteg/selenium-automation-framework/issues/7")
    @Test(groups = {"regression", "inventory"})
    public void categoryFieldIsPresentInProductCreationForm() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        inventory.openCreateProductForm();

        Assert.assertTrue(
                inventory.isCategoryFieldPresent(),
                "El campo de categoría no está presente en el formulario de creación de producto"
        );
    }

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-04 - As an administrator, I want to create a new product with all required details so that it becomes available for sale in the POS system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that attempting to create a product with a duplicate SKU displays an error message")
    @Owner("Salem Amortegui")
    @Issue("8")
    @Test(groups = {"regression", "inventory"})
    public void duplicateSKUShowsErrorMessage() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        // Create first product
        String productName1 = "QA_Dup_" + System.currentTimeMillis();
        String sku = "SKU_DUP_" + System.currentTimeMillis();

        inventory.createProduct(productName1, sku, "50.000,00", 20, 5);

        // Attempt to create second product with same SKU
        String productName2 = "QA_Dup2_" + System.currentTimeMillis();

        inventory.attemptCreateProduct(productName2, sku, "60.000,00", 10, 2);

        Assert.assertTrue(
                inventory.isErrorMessageDisplayed(),
                "The error message should be displayed when trying to create a product with a duplicate SKU"
        );
    }

    @Epic("POS System")
    @Feature("Inventory Management")
    @Story("RF-04 - As an administrator, I want to create a new product with all required details so that it becomes available for sale in the POS system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that attempting to create a product with a duplicate SKU displays a custom validation message instead of exposing the underlying platform")
    @Owner("Salem Amortegui")
    @Issue("https://github.com/SalemAmorteg/selenium-automation-framework/issues/8")
    @Test(groups = {"regression", "inventory"})
    public void duplicateSKUShowsCustomErrorMessage() {

        DashboardPage dashboard = doLogin(email, password);

        InventoryPage inventory = dashboard.goToInventory();

        // Create first product
        String productName1 = "QA_Dup_" + System.currentTimeMillis();
        String sku = "SKU_DUP_" + System.currentTimeMillis();

        inventory.createProduct(productName1, sku, "50.000,00", 20, 5);

        // Attempt to create second product with same SKU
        String productName2 = "QA_Dup2_" + System.currentTimeMillis();

        inventory.attemptCreateProduct(productName2, sku, "60.000,00", 10, 2);

        String errorMessage = inventory.getErrorMessage();

        Assert.assertFalse(
                errorMessage.contains("WooCommerce"),
                "The error message exposes the underlying platform 'WooCommerce' and should be replaced with a custom validation message"
        );
    }
}