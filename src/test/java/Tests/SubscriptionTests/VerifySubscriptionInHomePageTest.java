package Tests.SubscriptionTests;

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

public class VerifySubscriptionInHomePageTest extends BaseTest {

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
    public void VerifySubscriptionInHomepage(RegisterData testUser){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSubscriptionTextVisibleAfterScrolling(),
                "ERROR: subscription is not visible");
        homePage.
                fillEmail(testUser).
                clickOnSubscribeButton();
        Assert.assertTrue(homePage.isSubscriptionSuccessMessageVisible(),
                "ERROR : Success Message not Visible");

    }
}
