package Tests.BaseTests;

import Pages.AuthenticationPages.AccountCreatedPage;
import Pages.AuthenticationPages.SignUpPage;
import Pages.HomePage;
import Pages.AuthenticationPages.LoginSignupPage;
import Utils.JsonDataTypes.RegisterData;
import Utils.SeleniumFrameWork;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest extends SeleniumFrameWork {

    @BeforeMethod
    public void setUp()
    {
        Edges_initializeBrowser(); // add the ability to choose browser later
        Edges_navigateToURL("https://automationexercise.com/");
    }

//    @AfterMethod
//    public void tearDown(){
//        Edges_closeBrowser();
//    }
@AfterMethod
public void tearDown() {
    //clean up the account if we are logged in
    try {
        HomePage homePage = new HomePage(driver);
        if (homePage.isLoggedIn()) {
            homePage.clickOnDeleteAccountButton();
            System.out.println("Cleaned up: Account deleted in tearDown.");
        }
    } catch (Exception e) {
        // Ignore errors here. If cleanup fails.
        System.out.println("no need to logout ");
    }

    Edges_closeBrowser();
}



    protected HomePage BasicLogin(RegisterData user) {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: Homepage not visible");

        LoginSignupPage loginPage = homePage.click_login_or_signup();

        loginPage.login_enter_email_address(user.email);
        loginPage.login_enter_password(user.password);
        loginPage.login_click_login();

        return homePage;
    }

    // very basic signing up without deleting the account
    // used in any login test
    protected HomePage totalRegisterUserFromLoginPage(LoginSignupPage loginSignupPage, RegisterData user) {

        //*****************BASIC INFO********************
        loginSignupPage.signup_enter_name(user.name);
        loginSignupPage.signup_enter_email_address(user.email);

        //Click Signup
        SignUpPage signUpPage = loginSignupPage.signup_click_signup();
        Assert.assertTrue(signUpPage.isAccountInfoPageVisible(),
                "ERROR: 'Enter Account Information' is not visible");

        // *********************ALL DETAILS***********************
        AccountCreatedPage accountCreatedPage = signUpPage
                .fillAccountInformation(user)
                .clickCreateAccount();
        Assert.assertTrue(accountCreatedPage.isMessageCreatedTitleVisible(),
                "ERROR: 'Account Created' is not visible");

        //  Click Continue to go to Home Page
        HomePage homePageLoggedIn = accountCreatedPage.clickOnContinueButton();
        Assert.assertTrue(homePageLoggedIn.isLoggedIn(),
                "ERROR: Login failed, 'Logged in as' not visible");

        return homePageLoggedIn;
    }

    protected void registerUser(RegisterData user) {
        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginPage = homePage.click_login_or_signup();

        loginPage.signup_enter_name(user.name);
        loginPage.signup_enter_email_address(user.email);
        SignUpPage signUpPage = loginPage.signup_click_signup();

        // Fill Details & Create
        AccountCreatedPage accountCreated = signUpPage
                .fillAccountInformation(user)
                .clickCreateAccount();

        //LOGOUT so the actual test can start from the 'Login' screen
        accountCreated.clickOnContinueButton().clickLogoutButton();
    }
}
