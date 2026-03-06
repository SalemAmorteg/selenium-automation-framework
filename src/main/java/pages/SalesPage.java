package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SalesPage extends BasePage {

    // 🔹 Locators
    private By searchField = By.id("product-search");
    private By cartItemCounter = By.className("tiendo-quantity-value");
    private By totalAmount = By.id("cart-total-value");
    private By paymentMethodDropdown = By.id("metodo-pago");
    private By amountReceivedInput = By.id("dinero-recibido");
    private By confirmSaleButton = By.id("confirm-sale-btn");
    private By saleSummaryModal = By.xpath("//h3[normalize-space()='Confirmar Venta']");
    private By processSaleButton = By.id("process-sale-final-btn");
    private By successMessage = By.cssSelector(".tiendo-alert.tiendo-alert-success");

    public SalesPage() {
        super();
    }

    @Step("wait until sales page is loaded")
    public void waitUntilLoaded() {
        find(searchField);
    }

    @Step("check if sales page is loaded")
    public boolean isLoaded() {
        try {
            return find(searchField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Actions
    @Step("search for product: {productName}")
    public void searchProduct(String productName) {
        type(searchField, productName);
    }

    @Step("add product to cart: {productName}")
    public void addProductToCart(String productName) {

        By productNameLocator = By.xpath(
                "//div[@class='name' and normalize-space(text())='"
                        + productName + "']"
        );

        find(productNameLocator);

        By addButtonLocator = By.xpath(
                "//div[@class='name' and normalize-space(text())='"
                        + productName +
                        "']/ancestor::div[contains(@class,'product')]//button[contains(.,'Agregar')]"
        );

        click(addButtonLocator);

        waitForProductInCart(productName);
    }

    @Step("wait for product to appear in cart: {productName}")
    public void waitForProductInCart(String productName) {
        By cartItemLocator = By.xpath(
                "//div[contains(@class,'cart-item') and contains(.,'"
                        + productName + "')]"
        );
        find(cartItemLocator);
    }

    @Step("get cart item count")
    public int getCartItemCount() {
        String countText = find(cartItemCounter).getText().trim();
        return Integer.parseInt(countText);
    }

    @Step("get cart total amount")
    public String getCartTotal() {
        return find(totalAmount).getText().trim();
    }

    @Step("select payment method: {method}")
    public void selectPaymentMethod(String method) {
        Select select = new Select(find(paymentMethodDropdown));
        select.selectByVisibleText(method);
    }

    @Step("enter amount received: {amount}")
    public void enterAmountReceived(String amount) {
        WebElement input = find(amountReceivedInput);
        input.clear();
        input.sendKeys(amount);
        input.sendKeys(Keys.TAB);
    }

    @Step("click on confirm sale button")
    public void clickConfirmSale() {
        click(confirmSaleButton);
        find(saleSummaryModal);
    }

    @Step("click on confirm sale button in summary modal")
    public void processFinalSale() {
        click(processSaleButton);
    }

    @Step("Verify if sale was successful")
    public boolean isSaleSuccessful() {
        try {
            return find(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}