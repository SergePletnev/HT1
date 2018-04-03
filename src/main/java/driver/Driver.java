package driver;

import config.Config;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Browser;
import utils.BrowserManager;


public class Driver {
    private static WebDriver driver;

    private static Browser browser = Config.getBrowser();

    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case CHROME:
                    ChromeDriverManager.getInstance().setup();
                    driver = new ChromeDriver(BrowserManager.getChromeOptions());
                    break;
                case FIREFOX:
                    FirefoxDriverManager.getInstance().setup();
                    driver = new FirefoxDriver(BrowserManager.getFirefoxOptions());
                    break;
                default:
                    throw new NullPointerException("[No such browser. Please, use Chrome or Firefox.]");
            }
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
