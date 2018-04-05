package pages;

import dataobjects.User;
import elements.Button;
import elements.Form;
import elements.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage extends BasePage {
    private final String BASE_URL = "http://127.0.0.1:8080/securityRealm/addUser";

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form[@method='post']")
    private Form createAccountForm;

    @FindBy(xpath = "//input[@name='username' and contains (@type,'text')]")
    private Input userName;

    @FindBy(xpath = "//input[@name='password1' and contains (@type,'password')]")
    private Input password;

    @FindBy(xpath = "//input[@name='password2' and contains (@type,'password')]")
    private Input confirmPassword;

    @FindBy(xpath = "//input[@name='fullname' and contains (@type,'text')]")
    private Input fullName;

    @FindBy(xpath = "//input[@name='email' and contains (@type,'text')]")
    private Input email;

    @FindBy(id = "yui-gen1-button")
    private Button createUserButton;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean isCreateAccountFormPresent() {
        return createAccountForm.isPresent();
    }

    public boolean isFormElementssPresent() {
        return userName.isPresent() && password.isPresent() && confirmPassword.isPresent()
                && fullName.isPresent() && email.isPresent();
    }

    public boolean isFormElementssEmpty() {
        return userName.getText().equals("") && password.getText().equals("") && confirmPassword.getText().equals("")
                && fullName.getText().equals("") && email.getText().equals("");
    }

    public void createNewUser(User user) {
        userName.write(user.getName());
        password.write(user.getPassword());
        confirmPassword.write(user.getPassword());
        fullName.write(user.getFullName());
        email.write(user.getEmail());
        createUserButton.click();
    }

    public String getCreateUserButtonColor() {
        return createUserButton.getHexColor();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }
}
