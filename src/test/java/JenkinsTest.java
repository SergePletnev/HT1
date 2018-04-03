import dataobjects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class JenkinsTest extends BaseTest {

    private User testUser = new User("someuser", "somepassword", "Some Full Name", "some@addr.dom");
    private User noNameUser = new User("noName", "test", "", "test@addr.dom");

    @Test (description = "HT1-1: Test manage jenkins link works correctly")
    public void tstManageJenkins() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.clickManageJenkins();
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        Assert.assertTrue(manageJenkinsPage.isManageUsersPresent(), "[Element dt with text = 'Manage Users' is not found]");
        Assert.assertTrue(manageJenkinsPage.isCreateDeleteModifyPresent(),
                "[Element dd with text = 'Create/delete/modify users that can log in to this Jenkins' is not found]");
    }

    @Test (description = "HT1-2: Test manage users link works correctly")
    public void tstManageUsers() {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        manageJenkinsPage.openPage();
        manageJenkinsPage.clickManageUsers();
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        Assert.assertTrue(manageUsersPage.isCreateUserLinkPresent(), "[Link with text = 'Create User' is not found]");
    }

    @Test (description = "HT1-3: Test form for creation new user", priority = 1, groups = {"createDeleteUser"})
    public void tstCreateUserForm() {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.openPage();
        manageUsersPage.createUser();
        CreateUserPage createUserPage = new CreateUserPage(driver);
        Assert.assertTrue(createUserPage.isCreateAccountFormPresent(), "[Form of adding new account is not found]");
        Assert.assertTrue(createUserPage.isFormElementssPresent(), "[One or more form elements are not found]");
        Assert.assertTrue(createUserPage.isFormElementssEmpty(), "[One or more form elements are not empty]");
    }

    @Test (description = "HT1-4: Test creation of new user", priority = 2, groups = {"createDeleteUser"})
    public void tstCreateUser() {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.openPage();
        createUserPage.createNewUser(testUser);
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        Assert.assertTrue(manageUsersPage.isCreatedUserPresent(), "[New user (created) is not found]");
    }

    @Test (description = "HT1-5: Test presence of confirmation text", priority = 3, groups = {"createDeleteUser"})
    public void tstConfirmationDeleteUser() {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.openPage();
        manageUsersPage.deleteCreatedUser();
        DeleteUserConfirmationPage confirmationPage = new DeleteUserConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.getPageSource().contains("Are you sure about deleting the user from Jenkins?"),
                "[Confirmation text is not found on the page]");
    }

    @Test (description = "HT1-6: Test confirmation user delete", priority = 4, groups = {"createDeleteUser"})
    public void tstDeleteUser() {
        DeleteUserConfirmationPage confirmationPage = new DeleteUserConfirmationPage(driver);
        confirmationPage.openPage();
        confirmationPage.confirmUserDeletion();
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        Assert.assertFalse(manageUsersPage.isCreatedUserPresent(),
                "[Manage Users page contains element with text = 'someuser']");
        Assert.assertFalse(manageUsersPage.isDeleteUserLinkPresent(),
                "[Manage Users page contains href = 'user/someuser/delete']");
    }

    @Test (description = "HT1-7: Test presence of admin delete link")
    public void tstAdminHrefPresence() {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.openPage();
        Assert.assertFalse(manageUsersPage.isDeleteAdminLinkPresent(),
                "[Manage Users page contains href = 'user/admin/delete']");
    }

    @Test (description = "HT1-additional task 1: Test font of buttons to be clicked")
    public void tstButtonColor() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        try {
            Assert.assertEquals(loginPage.getLoginButtonColor(), "#4b758b");
        } catch (Exception e) {
            verificationErrors.append("Background color of login button is not '#4b758b': " + e.toString() + "\n");
        }
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.openPage();
        try {
            Assert.assertEquals(createUserPage.getCreateUserButtonColor(), "#4b758b");
        } catch (Exception e) {
            verificationErrors.append("Background color of create user button is not '#4b758b': " + e.toString() + "\n");
        }
        DeleteUserConfirmationPage deleteUserConfirmationPage = new DeleteUserConfirmationPage(driver);
        deleteUserConfirmationPage.openPage();
        try {
            Assert.assertEquals(deleteUserConfirmationPage.getDeleteUserButtonColor(), "#4b758b");
        } catch (Exception e) {
            verificationErrors.append("Background color of delete confirmation button is not '#4b758b': " + e.toString() + "\n");
        }
    }

    @Test  (description = "HT1-additional task 2: Test creation of user with empty name")
    public void tstCreationUserWithoutName() {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.openPage();
        createUserPage.createNewUser(noNameUser);
        Assert.assertTrue(createUserPage.getPageSource().contains("\"\" is prohibited as a full name for security reasons."),
                "[Creation page does not contain '\"\" is prohibited as a full name for security reasons.']");
    }

    @Test (description = "HT1-additional task 3: Test 'AUTO REFRESH' link cyclyc change")
    public void cyclicRefreshLinkChange() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.enableAutoRefresh();
        Assert.assertTrue(homePage.isDisableAutoRefreshLinkPresent());
        Assert.assertFalse(homePage.isEnableAutoRefreshLinkPresent());
        homePage.disableAutoRefresh();
        Assert.assertTrue(homePage.isEnableAutoRefreshLinkPresent());
        Assert.assertFalse(homePage.isDisableAutoRefreshLinkPresent());
    }

}
