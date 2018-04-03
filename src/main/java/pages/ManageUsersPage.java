package pages;

import elements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage extends BasePage {
    private final String BASE_URL = "http://127.0.0.1:8080/securityRealm";

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(text(),'Create User')]")
    private Link createUserLink;

    @FindBy(xpath = "//tr/td/a[text()='someuser']")
    private Link newUserLink;

    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private Link deleteUserLink;

    @FindBy(xpath = "//a[@href='user/admin/delete']")
    private Link deleteAdminLink;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void createUser() {
        createUserLink.click();
    }

    public void deleteCreatedUser() {
        deleteUserLink.click();
    }

    public boolean isCreateUserLinkPresent() {
        return createUserLink.isPresent();
    }

    public boolean isCreatedUserPresent() {
        return newUserLink.isPresent();
    }

    public boolean isDeleteUserLinkPresent() {
        return deleteUserLink.isPresent();
    }

    public boolean isDeleteAdminLinkPresent() {
        return deleteAdminLink.isPresent();
    }
}
