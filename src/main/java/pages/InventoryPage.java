package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitHelper;

public class InventoryPage extends BasePage {

    public InventoryPage() {
       super();
    }

    private static By addProductButton = By.id("add-product-btn");
    private By productNameField = By.id("product-nombre");
    private By productSKUField = By.id("product-sku");
    private By productActualStockField = By.id("product-stock");
    private By productMinStockField = By.id("product-stock-min");
    private By productPriceField = By.id("product-precio");
    private By submitButton = By.id("save-product");
    private By inventorySearch = By.id("inventory-search");
    private By editButton = By.cssSelector(".tiendo-btn.tiendo-btn-primary.edit-product");
    private By deleteButton = By.id("delete-product");
    private By tableBody = By.id("inventory-table-body");

    public static boolean isLoaded() {
        return WaitHelper.waitForVisibility(addProductButton).isDisplayed();
    }

    public void createProduct(String name,
                              String sku,
                              String price,
                              int stockActual,
                              int stockMin) {
        clickAddProductButton();

        fillProductForm(
                name,
                sku,
                String.valueOf(stockActual),
                String.valueOf(stockMin)
        );

        enterPrice(String.valueOf(price));

        clickSubmitButton();
    }

    public int getStockForProduct(String productName) {

        searchProduct(productName);
        waitForProductToAppear(productName);
        By stockCell = By.xpath(String.format(
                "//tr[contains(.,'%s')]/td[3]",
                productName
        ));
        WebElement stockElement = WaitHelper.waitForVisibility(stockCell);

        String stockText = stockElement.getText().trim();

        return Integer.parseInt(stockText);
    }





    private void clickAddProductButton() {
        WaitHelper.waitForClickable(addProductButton).click();
    }

    private void fillProductForm(String name, String sku, String stockActual, String stockMin) {

        driver.findElement(productNameField).sendKeys(name);
        driver.findElement(productSKUField).sendKeys(sku);
        driver.findElement(productActualStockField).sendKeys(stockActual);
        driver.findElement(productMinStockField).sendKeys(stockMin);

    }

    // Devuelve el locator dinámico del producto
    private By getProductRow(String productName) {
        return By.xpath("//tbody[@id='inventory-table-body']//tr[contains(.,'" + productName + "')]");
    }

    private void enterPrice(String price) {
        WebElement priceInput = WaitHelper.waitForClickable(productPriceField);

        priceInput.click();
        priceInput.clear();
        priceInput.sendKeys(Keys.CONTROL + "a");
        priceInput.sendKeys(Keys.DELETE);
        priceInput.sendKeys(price);
        priceInput.sendKeys(Keys.TAB); // dispara validación JS
    }

    private void clickSubmitButton() {
        WaitHelper.waitForClickable(submitButton).click();
    }

    public void searchProduct(String productName) {

        WebElement search = WaitHelper.waitForVisibility(inventorySearch);
        search.clear();
        search.sendKeys(productName);
    }

    public void waitForProductToAppear(String productName) {

        WaitHelper.waitForVisibility(getProductRow(productName));
        wait.until(ExpectedConditions.presenceOfElementLocated(getProductRow(productName)));
    }

    public void waitForProductToDisappear(String productName) {
        wait.until(ExpectedConditions.numberOfElementsToBe(getProductRow(productName), 0));
    }

    public boolean isProductPresent(String productName) {
       return driver.findElements(getProductRow(productName)).size() > 0;
    }

    public boolean isPriceUpdated(String productName) {

        By updatedRow = By.xpath("//tbody[@id='inventory-table-body']//tr[contains(.,'" + productName + "')]");

        try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(updatedRow));
        return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void deleteProduct(String productName) {
        By productRow = getProductRow(productName);

        // Asegura que el producto existe antes de eliminar
        wait.until(ExpectedConditions.presenceOfElementLocated(productRow));
        // Click edit
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        // Click delete
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        // Confirmar alerta
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        //Espera a que producto desaparezca del DOM
        wait.until(ExpectedConditions.numberOfElementsToBe(productRow, 0));
    }
}
