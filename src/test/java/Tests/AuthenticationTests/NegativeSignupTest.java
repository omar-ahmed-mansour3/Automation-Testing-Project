package Tests.AuthenticationTests;

import Pages.HomePage;
import Pages.AuthenticationPages.LoginSignupPage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class NegativeSignupTest extends BaseTest {
        static RegisterData[] ListOfRegisteringCredentials;
        @BeforeClass
        public void setUpClass() throws FileNotFoundException {
            // Make sure this filename matches the file inside src/test/java/TestingData/
            ListOfRegisteringCredentials =
                    RegisterUserHelper.ReadRegisterData("SignUpData.json");
        }

        @DataProvider(name = "RegisteringCredentials")
        public Object[] userCredentialsProvider() {
            return ListOfRegisteringCredentials;
        }

    @Test(dataProvider = "RegisteringCredentials")
    public void testLoginUser(RegisterData testUser){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: Homepage not visible");

        LoginSignupPage loginSignupPage = homePage.click_login_or_signup();

        Assert.assertTrue(loginSignupPage.isSignupTitleVisible(),
                "ERROR: signup title not visible");

        loginSignupPage.signup_enter_name(testUser.name);
        loginSignupPage.signup_enter_email_address(testUser.email);
        loginSignupPage.signup_click_signup();
        Assert.assertTrue(loginSignupPage.isEmailAlreadyUsed(),
                "ERROR: 'email already used' message is not displayed");



    }
}
