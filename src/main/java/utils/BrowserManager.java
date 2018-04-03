package utils;

import config.Config;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserManager {
    private WebDriver webDriver;

    public BrowserManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        HashMap<String, Object> firefoxPrefs = new HashMap<String, Object>();
        firefoxPrefs.put("browser.startup.homepage", "about:blank");
        firefoxOptions.setCapability("prefs", firefoxPrefs);
        return firefoxOptions;
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("browser.startup.homepage", "about:blank");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }

    public void maximize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int)toolkit.getScreenSize().getHeight();
        webDriver.manage().window().setSize(new Dimension(width, height));
    }

    public void setTimeOuts() {
        webDriver.manage().timeouts().implicitlyWait(Config.getImplicityWait(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Config.getPageLoadTimeout(), TimeUnit.SECONDS);
    }
}
