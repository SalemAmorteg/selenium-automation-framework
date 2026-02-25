package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    // ThreadLocal permite que cada hilo tenga su propia instancia del WebDriver
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Método para inicializar el driver
    public static void setDriver() {
        driver.set(new ChromeDriver());
    }

    // Método para obtener el driver actual del hilo
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Método para obtener el driver actual del hilo
    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
