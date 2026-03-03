package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CashRegisterPage extends BasePage {

    // Locators
    private By cashRegisterIdentifier = By.xpath("//h1[contains(text(),'Cierre de Caja')]");
    private By openButton = By.id("go-to-open-btn");
    private By openAmountInput = By.id("fondo-inicial");
    private By startShiftButton = By.id("open-cash-btn");
    private By closeAmountInput = By.id("efectivo-contado");
    private By closeButton = By.id("close-cash-btn");
    private By modalSuccessMessage = By.id("modal-result-title");
    private By modalCloseRegisterConfirmationButton =
            By.cssSelector(".tiendo-btn.close-modal-result");

    public void waitUntilLoaded() {
        find(cashRegisterIdentifier);
    }

    public boolean isLoadedWhenClosed() {
        return isVisible(openButton);
    }

    public boolean isLoadedWhenOpen() {
        return isVisible(closeButton);
    }

    // 🔓 Abrir caja
    public void openRegister(String amount) {

        click(openButton);

        WebElement input = find(openAmountInput);
        input.click();
        input.clear();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(amount);
        input.sendKeys(Keys.TAB);

        click(startShiftButton);
    }

    public boolean isRegisterOpen() {
        return isVisible(closeButton);
    }

    // 🔒 Cerrar caja
    public void closeRegister(String amount) {

        WebElement input = find(closeAmountInput);
        input.click();
        input.clear();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(amount);
        input.sendKeys(Keys.TAB);

        click(closeButton);
    }

    public void confirmCloseRegisterModal() {
        find(modalSuccessMessage);
        click(modalCloseRegisterConfirmationButton);
    }

    public boolean isRegisterClosed() {
        return isVisible(openButton);
    }
}
