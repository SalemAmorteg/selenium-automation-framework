package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.WaitHelper;

public class SalesPage extends BasePage {

    private static By inventorySearch = By.id("product-search");
    private By productRow = By.xpath("//tr[contains(.,'%s')]");
    private By addButton = By.xpath("//tr[contains(.,'%s')]//button[contains(.,'Agregar')]");
    private By cartItem = By.xpath("//div[@class='cart-item' and contains(.,'%s')]");
    private By cartItemCounter = By.className("tiendo-quantity-value");
    private By totalAmount = By.id("cart-total-value");
    private By paymentMethodDropdown = By.id("metodo-pago");
    private By amountReceivedInput = By.id("dinero-recibido");
    private By confirmSaleButton = By.id("confirm-sale-btn");
    private By saleSummaryModal = By.xpath("(//h3[normalize-space()='Confirmar Venta'])[1]");
    private By processSaleButton = By.id("process-sale-final-btn");
    private By successMessage = By.cssSelector(".tiendo-alert.tiendo-alert-success");

    public static boolean isLoaded() {
        return WaitHelper.waitForVisibility(inventorySearch).isDisplayed();
    }

    public void addProductToCart(String productName) {

        //  Esperar que barra de búsqueda esté lista
        WebElement searchInput = WaitHelper.waitForVisibility(inventorySearch);

        searchInput.clear();
        searchInput.sendKeys(productName);

        //  Esperar que el producto aparezca en la tabla
        By dynamicProductName = By.xpath(String.format(
                "//div[@class='name' and normalize-space(text())='%s']",
                productName
        ));

        WaitHelper.waitForVisibility(dynamicProductName);

        //  Click en botón Agregar del producto específico
        By dynamicAddButton = By.xpath(String.format(
                "//div[@class='name' and normalize-space(text())='%s']/ancestor::div[contains(@class,'product')]//button[contains(.,'Agregar')]",
                productName
        ));

        WaitHelper.waitForClickable(dynamicAddButton).click();

        //  Esperar que aparezca en carrito
        By dynamicCartItem = By.xpath(String.format(
                "//div[contains(@class,'cart-item') and contains(.,'%s')]",
                productName
        ));

        WaitHelper.waitForVisibility(dynamicCartItem);
    }

    public void completeSale(String paymentMethod) {

        //  Validar que haya productos en carrito
        WebElement counter = WaitHelper.waitForVisibility(cartItemCounter);
        int itemCount = Integer.parseInt(counter.getText());

        if (itemCount == 0) {
            throw new RuntimeException("No items in cart. Cannot complete sale.");
        }

        //  Obtener total
        WebElement totalElement = WaitHelper.waitForVisibility(totalAmount);
        String totalText = totalElement.getText()
                .replace("$", "")
                .replace(",", "")
                .trim();

        double total = Double.parseDouble(totalText);

        //  Seleccionar método de pago
        WebElement dropdown = WaitHelper.waitForClickable(paymentMethodDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(paymentMethod);

        //  Ingresar dinero recibido
        WebElement amountInput = WaitHelper.waitForVisibility(amountReceivedInput);
        amountInput.clear();
        amountInput.sendKeys(String.valueOf(total)); // pagamos exacto
        amountInput.sendKeys(Keys.TAB);

        //  Esperar que botón esté habilitado
        WaitHelper.waitForClickable(confirmSaleButton).click();

        //  Esperar modal resumen
        WaitHelper.waitForVisibility(saleSummaryModal);

        //  Procesar y finalizar venta
        WaitHelper.waitForClickable(processSaleButton).click();

    }

    public String getSuccessMessage() {
        return WaitHelper.waitForVisibility(successMessage).getText();
    }
}
