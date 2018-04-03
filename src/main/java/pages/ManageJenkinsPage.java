package pages;

import elements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage extends BasePage {
    private final String BASE_URL = "http://127.0.0.1:8080/manage";

    @FindBy(xpath = "//dt[contains(text(),'Manage Users')]")
    private Link manageUsersLink;

    @FindBy(xpath = "//dd[contains(text(),'Create/delete/modify users that can log in to this')]")
    private Link createDeleteModifyLink;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public void clickManageUsers() {
        manageUsersLink.click();
    }

    public boolean isManageUsersPresent() {
        return manageUsersLink.isPresent();
    }

    public boolean isCreateDeleteModifyPresent() {
        return createDeleteModifyLink.isPresent();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }
}
