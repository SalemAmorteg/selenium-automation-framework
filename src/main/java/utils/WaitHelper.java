package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
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
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}