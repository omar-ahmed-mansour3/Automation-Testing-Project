package Tests.CheckoutTests;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.AuthenticationPages.LoginSignupPage;
import Pages.CartRelatedPages.CartPage;
import Pages.CartRelatedPages.CheckoutPage;
import Pages.CartRelatedPages.PaymentDonePage;
import Pages.HomePage;
import Tests.BaseTests.CheckoutTestBase;
import Utils.JsonDataTypes.RegisterAndCheckoutData;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterAndCheckoutDataHelper;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class VerifyAddressInCheckoutTest extends CheckoutTestBase {

    @DataProvider(name = "CheckoutFlowData")
    public Object[] getCombinedData() throws FileNotFoundException {
        return RegisterAndCheckoutDataHelper.readData("RegisterAndCheckoutData.json");
    }

    @Test(dataProvider = "CheckoutFlowData")
    public void verifyAddressInCheckoutTest(RegisterAndCheckoutData data){

            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageVisible(),
                    "ERROR HOMEPAGE NOT VISIBLE");

            //********************SIGNUP**********************
            LoginSignupPage loginSignupPage = homePage.click_login_or_signup();

            HomePage homePage1 =  totalRegisterUserFromLoginPage(loginSignupPage, data.user);


            //*******************ADDING ITEMS TO CART ***********************
            addProductFromHomePage(homePage1, 1, 1);//use method from parent
            homePage1.clickOnContinueShoppingButton();

            //***************************GOING TO CART***************
            CartPage cartPage = homePage1.clickOnCart();
            CheckoutPage checkoutPage = cartPage.clickOnProceedToCheckoutButtonWhileLoggedIn();

            //**********************CHECK THE DETAILS IN THE CHECKOUT PAGE****************
            // ******* VERIFY ADDRESS DETAILS *******
            verifyCheckoutAddress(checkoutPage, data);

            // ************** Delete Account **************
            DeletedAccountPage deletedAccountPage = checkoutPage.clickOnDeleteAccountButton();
            Assert.assertTrue(deletedAccountPage.IsDeleted(), "ERROR: Account was not deleted successfully");

        }



}
