package pages;

import base.BasePage;
import io.qameta.allure.Step;
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

    @Step("Wait until Cash Register page is fully loaded")
    public void waitUntilLoaded() {
        find(cashRegisterIdentifier);
    }

    @Step("Verify that Cash Register page is loaded when the cash register is closed")
    public boolean isLoadedWhenClosed() {
        return isVisible(openButton);
    }

    @Step("Verify that Cash Register page is loaded when the cash register is opened")
    public boolean isLoadedWhenOpen() {
        return isVisible(closeButton);
    }

    @Step("Open the cash register")
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

    @Step("Verify that the cash register is open")
    public boolean isRegisterOpen() {
        return isVisible(closeButton);
    }

    @Step("Close the Cash Register")
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

    @Step("Confirm cash register closing in modal")
    public void confirmCloseRegisterModal() {
        find(modalSuccessMessage);
        click(modalCloseRegisterConfirmationButton);
    }

    @Step("Verify that the cash register is closed")
    public boolean isRegisterClosed() {
        return isVisible(openButton);
    }
}
