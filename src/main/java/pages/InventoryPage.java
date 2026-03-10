package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;
import io.qameta.allure.Step;

public class InventoryPage extends BasePage {

    // 🔹 Locators
    private By inventoryIdentifier = By.id("add-product-btn");

    private By productNameField = By.id("product-nombre");
    private By productSKUField = By.id("product-sku");
    private By productActualStockField = By.id("product-stock");
    private By productMinStockField = By.id("product-stock-min");
    private By productPriceField = By.id("product-precio");
    private By productDescriptionField = By.id("product-description");
    private By productCategoryField = By.id("product-category");
    private By submitButton = By.id("save-product");

    private By inventorySearch = By.id("inventory-search");
    private By editButton = By.cssSelector(".edit-product");
    private By deleteButton = By.id("delete-product");

    private By errorMessage = By.className("tiendo-alert-error");

    @Step("Wait until Cash Register page is fully loaded")
    public void waitUntilLoaded() {
        find(inventoryIdentifier);
    }

    @Step("Verify that Inventory page is displayed")
    public boolean isLoaded() {
        try {
            return getDriver().findElement(inventoryIdentifier).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Create a product")
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

    @Step("Get the actual stock of a product")
    public int getStockForProduct(String productName) {

        searchProduct(productName);
        waitForProductToAppear(productName);

        By stockCell = By.xpath(
                "//tr[contains(.,'" + productName + "')]/td[3]"
        );

        String stockText = find(stockCell).getText().trim();

        return Integer.parseInt(stockText);
    }

    @Step("Delete a product")
    public void deleteProduct(String productName) {

        By productRow = getProductRow(productName);

        find(productRow);

        click(editButton);
        click(deleteButton);

        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        waitForProductToDisappear(productName);
    }

    @Step("Enter product price")
    public void enterPrice(String price) {
        WebElement priceInput = find(productPriceField);

        priceInput.click();
        priceInput.clear();
        priceInput.sendKeys(Keys.CONTROL + "a");
        priceInput.sendKeys(Keys.DELETE);
        priceInput.sendKeys(price);
        priceInput.sendKeys(Keys.TAB);
    }

    @Step("Search a product")
    public void searchProduct(String productName) {
        type(inventorySearch, productName);
    }

    @Step("Wait for product presence")
    public void waitForProductToAppear(String productName) {

        By row = getProductRow(productName);

        WaitHelper.waitForPresence(row);
    }

    @Step("Wait until product disappear")
    public void waitForProductToDisappear(String productName) {
        WaitHelper.waitForInvisibility(getProductRow(productName));
    }

    @Step("Verify that the product is present")
    public boolean isProductPresent(String productName) {
        return getDriver().findElements(getProductRow(productName)).size() > 0;
    }

    private By getProductRow(String productName) {
        return By.xpath(
                "//tbody[@id='inventory-table-body']//tr[contains(.,'"
                        + productName + "')]"
        );
    }

    @Step("Click the edit product button")
    public void clickEditButton(String productName) {

        By editButtonForProduct = By.xpath(
                "//tr[contains(.,'" + productName + "')]//button[contains(@class,'edit-product')]"
        );

        click(editButtonForProduct);
    }

    @Step("Click the submit button to save changes")
    public void clickSubmitButton() {
        click(submitButton);
    }

    @Step("Verify that the product price is updated")
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

    @Step("Verify that the description field is present")
    public boolean isDescriptionFieldPresent() {
        try {
            find(productDescriptionField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify that the category field is present")
    public boolean isCategoryFieldPresent() {
        try {
            find(productCategoryField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Open create product form")
    public void openCreateProductForm() {
        click(inventoryIdentifier);
    }

    @Step("Attempt to create a product (for testing errors)")
    public void attemptCreateProduct(String name,
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
    }

    @Step("Get the error message from the form")
    public String getErrorMessage() {
        try {
            return find(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Verify if the error message is displayed")
    public boolean isErrorMessageDisplayed() {
        try {
            return find(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}