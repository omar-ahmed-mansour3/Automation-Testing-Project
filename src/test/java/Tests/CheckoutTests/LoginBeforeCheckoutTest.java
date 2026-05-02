package Tests.CheckoutTests;

import Pages.AuthenticationPages.DeletedAccountPage;
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

public class LoginBeforeCheckoutTest extends CheckoutTestBase {

    @DataProvider(name = "CheckoutFlowData")
    public Object[] getCombinedData() throws FileNotFoundException {
        return RegisterAndCheckoutDataHelper.readData("RegisterAndCheckoutData.json");
    }

    @Test(dataProvider = "CheckoutFlowData")
    public void loginBeforeCheckoutTest(RegisterAndCheckoutData data) {
        // We register them, then logout immediately.
        registerUser(data.user);

        //***************START A CLEAN TEST FROM 0***********
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "ERROR: Homepage not visible");

        //***************login***************************************
        //used from BaseTest
        BasicLogin(data.user);

        // *****Verify Logged In ***
        Assert.assertTrue(homePage.isLoggedIn(), "ERROR: Login failed");

        // ** Add Products ***
        addProductFromHomePage(homePage,1,1);
        homePage.clickOnContinueShoppingButton();


        // *******Go to Cart ***
        CartPage cartPage = homePage.clickOnCart();

        // ***Proceed to Checkout ***
        CheckoutPage checkoutPage = cartPage.clickOnProceedToCheckoutButtonWhileLoggedIn();

        // ******* VERIFY ADDRESS DETAILS *******
        //use CheckoutTestBase parent class method
        verifyCheckoutAddress(checkoutPage, data);

        //************PAYMENT*************************
        //use CheckoutTestBase parent class method
        String comment = "bla bla bla 123 @#$%";
        PaymentDonePage paymentDonePage = placeOrderAndPay(checkoutPage,comment,data);

        // ***Delete Account ***
        DeletedAccountPage deletedAccountPage = paymentDonePage.clickDeleteAccountButton();
        Assert.assertTrue(deletedAccountPage.IsDeleted(), "ERROR: Account not deleted");
    }
}