package pages;

import elements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    private final String BASE_URL = "http://127.0.0.1:8080";

    @FindBy(xpath = "//div[@class='login']//b[contains(text(),'log in')]")
    private Link logInLink;

    @FindBy(linkText = "Manage Jenkins")
    private Link manageJenkinsLink;

    @FindBy(xpath = "//a[@href='?auto_refresh=true']")
    private Link enableAutoRefreshLink;

    @FindBy(xpath = "//a[@href='?auto_refresh=false']")
    private Link disableAutoRefreshLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void enableAutoRefresh() {
        enableAutoRefreshLink.click();
    }

    public void disableAutoRefresh() {
        disableAutoRefreshLink.click();
    }

    public void clickManageJenkins() {
        manageJenkinsLink.click();
    }

    public boolean isDisableAutoRefreshLinkPresent() {
        return disableAutoRefreshLink.isPresent();
    }

    public boolean isEnableAutoRefreshLinkPresent() {
        return enableAutoRefreshLink.isPresent();
    }
}
