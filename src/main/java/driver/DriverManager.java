package driver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    // ThreadLocal permite que cada hilo tenga su propia instancia del WebDriver
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Método para inicializar el driver
    public static void setDriver() {
        String  browser = ConfigReader.get("browser");

        if(browser == null) {
        }
        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case "safari":

                if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                    throw new RuntimeException("Safari is only supported on macOS");
                }

                driver.set(new SafariDriver());
                break;

            default:
                throw new RuntimeException("Browser not supported " + browser);
        }
    }

    // Método para obtener el driver actual del hilo
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Método para obtener el driver actual del hilo
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
