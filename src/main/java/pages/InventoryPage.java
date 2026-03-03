package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class InventoryPage extends BasePage {

    // 🔹 Locators
    private By inventoryIdentifier = By.id("add-product-btn");

    private By productNameField = By.id("product-nombre");
    private By productSKUField = By.id("product-sku");
    private By productActualStockField = By.id("product-stock");
    private By productMinStockField = By.id("product-stock-min");
    private By productPriceField = By.id("product-precio");
    private By submitButton = By.id("save-product");

    private By inventorySearch = By.id("inventory-search");
    private By editButton = By.cssSelector(".edit-product");
    private By deleteButton = By.id("delete-product");

    // 🔹 Synchronization
    public void waitUntilLoaded() {
        find(inventoryIdentifier);
    }

    public boolean isLoaded() {
        try {
            return getDriver().findElement(inventoryIdentifier).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Business Actions

    public void createProduct(String name,
                              String sku,
                              String price,
                              int stockActual,
                              int stockMin) {

        click(inventoryIdentifier);

        type(productNameField, name);
        type(productSKUField, sku);
        type(productActualStockField, String.valueOf(stockActual));
        type(productMinStockField, String.valueOf(stockMin));

        enterPrice(price);

        click(submitButton);
        System.out.println(name + " " + sku);
        searchProduct(name);
        waitForProductToAppear(name);
    }

    public int getStockForProduct(String productName) {

        searchProduct(productName);
        waitForProductToAppear(productName);

        By stockCell = By.xpath(
                "//tr[contains(.,'" + productName + "')]/td[3]"
        );

        String stockText = find(stockCell).getText().trim();

        return Integer.parseInt(stockText);
    }

    public void deleteProduct(String productName) {

        By productRow = getProductRow(productName);

        find(productRow);

        click(editButton);
        click(deleteButton);

        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        waitForProductToDisappear(productName);
    }

    // 🔹 Helpers

    public void enterPrice(String price) {
        WebElement priceInput = find(productPriceField);

        priceInput.click();
        priceInput.clear();
        priceInput.sendKeys(Keys.CONTROL + "a");
        priceInput.sendKeys(Keys.DELETE);
        priceInput.sendKeys(price);
        priceInput.sendKeys(Keys.TAB);
    }

    public void searchProduct(String productName) {
        type(inventorySearch, productName);
    }

    public void waitForProductToAppear(String productName) {

        By row = getProductRow(productName);

        WaitHelper.waitForPresence(row);
    }

    public void waitForProductToDisappear(String productName) {
        WaitHelper.waitForInvisibility(getProductRow(productName));
    }

    public boolean isProductPresent(String productName) {
        return getDriver().findElements(getProductRow(productName)).size() > 0;
    }

    private By getProductRow(String productName) {
        return By.xpath(
                "//tbody[@id='inventory-table-body']//tr[contains(.,'"
                        + productName + "')]"
        );
    }

    public void clickEditButton(String productName) {

        By editButtonForProduct = By.xpath(
                "//tr[contains(.,'" + productName + "')]//button[contains(@class,'edit-product')]"
        );

        click(editButtonForProduct);
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public boolean isPriceUpdated(String productName, String expectedPrice) {

        try {

            By priceCellLocator = By.xpath(
                    "//tbody[@id='inventory-table-body']//tr[td[contains(text(),'"
                            + productName + "')]]/td[5]"
            );

            String expectedNormalized = expectedPrice.replaceAll("[^0-9]", "");

            WaitHelper.waitForTextToMatch(priceCellLocator, expectedNormalized);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}