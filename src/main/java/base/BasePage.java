package base;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public abstract class BasePage {

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected WebElement find(By locator) {
        return WaitHelper.waitForVisibility(locator);
    }

    protected void click(By locator) {
        WaitHelper.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = WaitHelper.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isVisible(By locator) {
        return WaitHelper.waitForVisibility(locator).isDisplayed();
    }

    protected void pressEnter(By locator) {
        WaitHelper.waitForVisibility(locator).sendKeys(org.openqa.selenium.Keys.ENTER);
    }
}