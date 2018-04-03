package steps;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserManager;

public class Steps {
    private WebDriver driver;

    public void initBrowser()
    {
        driver = Driver.getDriver();
        BrowserManager browserManager = new BrowserManager(driver);
        browserManager.setTimeOuts();
        browserManager.maximize();
    }

    public void closeDriver()
    {
        Driver.closeDriver();
    }

    public void loginJenkins(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public void logOut() {
        BasePage homePage = new HomePage(driver);
        homePage.logOut();
    }
}
