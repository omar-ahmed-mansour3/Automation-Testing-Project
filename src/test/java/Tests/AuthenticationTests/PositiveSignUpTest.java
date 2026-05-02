package Tests.AuthenticationTests;

import Pages.*;
import Pages.AuthenticationPages.AccountCreatedPage;
import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.AuthenticationPages.LoginSignupPage;
import Pages.AuthenticationPages.SignUpPage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class PositiveSignUpTest extends BaseTest {

    static RegisterData[] ListOfRegisteringCredentials;
    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        ListOfRegisteringCredentials =
                RegisterUserHelper.ReadRegisterData("SignUpData.json");
    }

    @DataProvider(name = "RegisteringCredentials")
    public Object[] userCredentialsProvider() {
        return ListOfRegisteringCredentials;
    }

    @Test(dataProvider = "RegisteringCredentials")
    public void testLoginUser(RegisterData testUser){
        // in HOMEPAGE

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: Homepage not visible");
        //*************go to log in or signup page*********************
        LoginSignupPage loginSignupPage = homePage.click_login_or_signup();

        //*****************inherited method*********************************
        HomePage homePage1 = totalRegisterUserFromLoginPage(loginSignupPage , testUser);

        Assert.assertTrue(homePage1.isLoggedIn(),
                "ERROR: Login failed, 'Logged in as' not visible");
        // delete and check
        DeletedAccountPage deletedAccountPage = homePage.clickOnDeleteAccountButton();
        Assert.assertTrue(deletedAccountPage.IsDeleted());



    }




}
