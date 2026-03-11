package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.ConfigReader;

import java.time.Duration;

public class WaitHelper {

    private static final int TIMEOUT = ConfigReader.getInt("timeout");

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );
    }

    public static WebElement waitForVisibility(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static boolean waitForInvisibility(By locator) {
        return getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForPresence(By locator) {
        return getWait().until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public static void waitUntil(java.util.function.Function<WebDriver, Boolean> condition) {
        getWait().until(condition);
    }

    public static void waitForTextToMatch(By locator, String expectedNormalized) {
        getWait().until(driver -> {
            String text = driver.findElement(locator).getText();
            String normalized = text.replaceAll("[^0-9]", "");
            return normalized.equals(expectedNormalized);
        });
    }

    public static WebElement waitForEnabled(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
}