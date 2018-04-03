package pages;

import elements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DeleteUserConfirmationPage extends BasePage {
    private final String BASE_URL = "http://127.0.0.1:8080/securityRealm/user/someuser/delete";

    @FindBy(id = "yui-gen1-button")
    private Button deleteUserButton;

    public DeleteUserConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void confirmUserDeletion() {
        deleteUserButton.click();
    }

    public String getDeleteUserButtonColor() {
        return deleteUserButton.getHexColor();
    }
}
