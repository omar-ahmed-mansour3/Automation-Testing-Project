package Tests.CheckoutTests;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.AuthenticationPages.LoginSignupPage;
import Pages.CartRelatedPages.CartPage;
import Pages.CartRelatedPages.CheckoutPage;
import Pages.CartRelatedPages.PaymentDonePage;
import Pages.HomePage;
import Tests.BaseTests.CheckoutTestBase;
import Utils.JsonDataTypes.RegisterAndCheckoutData;
import Utils.jsonHelpers.RegisterAndCheckoutDataHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class RegisterWhileCheckoutTest extends CheckoutTestBase {


    @DataProvider(name = "CheckoutFlowData")
    public Object[] getCombinedData() throws FileNotFoundException {
        return RegisterAndCheckoutDataHelper.readData("RegisterAndCheckoutData.json");
    }

    @Test(dataProvider = "CheckoutFlowData")
    public void registerWhileCheckoutTest(RegisterAndCheckoutData data) {

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "ERROR HOMEPAGE NOT VISIBLE");

        // ************** Add product to cart(inherited) **************
        addProductFromHomePage(homePage,1,1);

        //************************go to cart page********************
        CartPage cartPage = homePage.clickOnContinueShoppingButton().clickOnCart();
        //*************try to check out while logged out**************
        cartPage.clickOnProceedToCheckoutButtonWhileloggedOut();

        //*******************go to signup from cart page**********************
        Assert.assertTrue(cartPage.isLoginOverlayVisible(), "ERROR: overlay not visible");
        LoginSignupPage loginSignupPage = cartPage.clickOnOverlayLogin();

        // ********
        // **************************** Sign Up Process ******************************

        HomePage homePage1 =  totalRegisterUserFromLoginPage(loginSignupPage, data.user);


        // ************** Checkout Process while logged in **************
        CartPage cartPageLoggedIn = homePage1.clickOnCart();
        CheckoutPage checkoutPage = cartPageLoggedIn.clickOnProceedToCheckoutButtonWhileLoggedIn();

        // ******* VERIFY ADDRESS DETAILS *******
        verifyCheckoutAddress(checkoutPage, data);

        // ************** Payment(use inherited method) **************

        PaymentDonePage paymentDonePage = placeOrderAndPay(checkoutPage,"bla bla 123 !@##%",
                data);

        // Verify "Order Placed!" message
        Assert.assertTrue(paymentDonePage.isOrderPlacedSuccessMessageVisible(),
                "ERROR: Order Placed Success Message NOT Visible!");


        // ************** Delete Account **************
        DeletedAccountPage deletedAccountPage = paymentDonePage.clickDeleteAccountButton();
        Assert.assertTrue(deletedAccountPage.IsDeleted(), "ERROR: Account was not deleted successfully");
    }
}