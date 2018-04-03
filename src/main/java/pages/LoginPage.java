package pages;

import elements.Button;
import elements.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private final String BASE_URL = "http://127.0.0.1:8080/login";

    @FindBy(name = "j_username")
    private Input loginInput;

    @FindBy(name = "j_password")
    private Input passwordInput;

    @FindBy(id = "yui-gen1-button")
    private Button loginSubmitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public String getLoginButtonColor() {
        return  loginSubmitButton.getHexColor();
    }

    public void login(String username, String password) {
        loginInput.fill(username);
        passwordInput.fill(password);
        loginSubmitButton.click();
    }

}
