package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;


public class CashRegisterPage extends BasePage {

    //Locators ("//h1[contains(text(), 'Dashboard de Tendero')]")
    private static By openButton = By.id("go-to-open-btn");
    private By openAmountInput = By.id("fondo-inicial");
    private By startShiftButton = By.id("open-cash-btn");
    private By closeAmountInput = By.id("efectivo-contado");
    private static By closeButton = By.id("close-cash-btn");
    private By modalSuccessMessage = By.id("modal-result-title");
    private By modalCloseRegisterConfirmationButton = By.cssSelector(".tiendo-btn.close-modal-result");

    //  Validar carga con caja cerrada
    public static boolean isLoaded() {
        return WaitHelper.waitForVisibility(openButton).isDisplayed();
    }
    //  Validar carga con caja abierta
    public static boolean isLoadedWhenCashIsOpen() {
        return WaitHelper.waitForVisibility(closeButton).isDisplayed();
    }

    //  Abrir caja
    public void openRegister(String amount) {
        WaitHelper.waitForClickable(openButton).click();
        WebElement amountToOpenInput = WaitHelper.waitForClickable(openAmountInput);
        amountToOpenInput.click();
        amountToOpenInput.clear();
        amountToOpenInput.sendKeys(Keys.CONTROL + "a");
        amountToOpenInput.sendKeys(Keys.DELETE);
        amountToOpenInput.sendKeys(Keys.TAB);
        WaitHelper.waitForVisibility(openAmountInput).sendKeys(String.valueOf(amount));
        WaitHelper.waitForClickable(startShiftButton).click();
    }

    public boolean isRegisterOpen() {
        return WaitHelper.waitForVisibility(closeButton).isDisplayed();
    }

    //  Cerrar caja
    public void closeRegister(String amount) {
        WebElement amountToCloseInput = WaitHelper.waitForClickable(closeAmountInput);
        amountToCloseInput.click();
        amountToCloseInput.clear();
        amountToCloseInput.sendKeys(Keys.CONTROL + "a");
        amountToCloseInput.sendKeys(Keys.DELETE);
        amountToCloseInput.sendKeys(Keys.TAB);
        WaitHelper.waitForVisibility(closeAmountInput).sendKeys(String.valueOf(amount));
        WaitHelper.waitForVisibility(closeButton).click();
    }

    public void modalCloseRegisterConfirmation() {
        WaitHelper.waitForVisibility(modalSuccessMessage).isDisplayed();
        WaitHelper.waitForClickable(modalCloseRegisterConfirmationButton).click();
    }

    public boolean isRegisterClosed() {
        return WaitHelper.waitForVisibility(openButton).isDisplayed();
    }
}
