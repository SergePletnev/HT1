import config.Config;
import dataobjects.User;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserManager;

public abstract class BaseTest {
    protected WebDriver driver;
    protected StringBuffer verificationErrors = new StringBuffer();

    private User existingUser = new User("admin", "admin", "Admin", "afmin@addr.com");

    @BeforeClass
    public void setupClass() throws Exception {
        Config.loadConfig();
        driver = Driver.getDriver();
        BrowserManager browserManager = new BrowserManager(driver);
        browserManager.setTimeOuts();
        browserManager.maximize();
    }

    @BeforeMethod
    public void setupMethod() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(existingUser.getName(), existingUser.getPassword());
    }

    @AfterMethod
    public void teardownMethod() {
        BasePage homePage = new HomePage(driver);
        homePage.logOut();
    }

    @AfterClass
    public void teardownClass() {
        Driver.closeDriver();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}
