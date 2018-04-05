package pages;

import decorator.CustomFieldDecorator;
import elements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage
{
    protected WebDriver driver;

    @FindBy(xpath = "//div[@class='login']//a[@href='/logout']")
    private Link logOutLink;

    public abstract void openPage();

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        //using decorator to create custom elements
        PageFactory.initElements(new CustomFieldDecorator(this.driver), this);
    }

    public void logOut() {
        logOutLink.click();
    }
}