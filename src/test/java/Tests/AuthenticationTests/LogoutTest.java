package Tests.AuthenticationTests;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.HomePage;
import Pages.AuthenticationPages.LoginSignupPage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class LogoutTest extends BaseTest {

    static RegisterData[] ListOfRegisteringCredentials;

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        ListOfRegisteringCredentials = RegisterUserHelper.ReadRegisterData("SignUpData.json");
    }

    // 3. Create the user before the test runs
    @BeforeMethod
    public void registerUserForTest(Object[] testArgs){
        RegisterData currentUser = (RegisterData) testArgs[0];
        registerUser(currentUser);
    }

    @DataProvider(name = "usersCredentials")
    public Object[] userCredentialsProvider() {
        return ListOfRegisteringCredentials;
    }
    @Test(dataProvider = "usersCredentials")
    public void testLoginUser(RegisterData testUser){

        HomePage homePage = BasicLogin(testUser);
        Assert.assertTrue(homePage.isLoggedIn(),
                "ERROR: Login failed, 'Logged in as' not visible");
        LoginSignupPage loginSignupPage= homePage.clickLogoutButton();

        Assert.assertTrue(loginSignupPage.isInLoginPage(),
                "ERROR: user was not transferred to login page after logout");

        //login again to delete account
        loginSignupPage.login_enter_email_address(testUser.email);
        loginSignupPage.login_enter_password(testUser.password);
        HomePage homePage2 = loginSignupPage.login_click_login();

        // 4. Delete Account
        DeletedAccountPage deletedAccountPage = homePage2.clickOnDeleteAccountButton();
        Assert.assertTrue(deletedAccountPage.IsDeleted(), "ERROR: Account cleanup failed");


    }

}
