package Tests.SubscriptionTests;

import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class VerifySubscriptionInCartTest extends BaseTest {

    static RegisterData[] testEmails;

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        testEmails = RegisterUserHelper.ReadRegisterData("SignUpData.json");
    }

    @DataProvider(name = "emailList")
    public Object[] userCredentialsProvider() {
        return testEmails;
    }
    @Test(dataProvider = "emailList")
    public void VerifySubscriptionInCart(RegisterData testUser){

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.clickOnCart();

        Assert.assertTrue(cartPage.isSubscriptionTextVisibleAfterScrolling(),
                "ERROR: subscription is not visible");
        cartPage.
                fillEmail(testUser).
                clickOnSubscribeButton();
        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible(),
                "ERROR : Success Message not Visible");

    }
}
