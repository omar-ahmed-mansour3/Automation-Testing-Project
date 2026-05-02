package Tests.AuthenticationTests;

import Pages.HomePage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class NegativeLoginTest extends BaseTest {

    static RegisterData[] ListOfBadCredentials;

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        // CHANGED: Use RegisterUserHelper. GSON will fill email/pass and leave name/address as null.
        ListOfBadCredentials = RegisterUserHelper.ReadRegisterData("IncorrectLoginCredentials.json");
    }

    @DataProvider(name = "badCredentials")
    public Object[] userCredentialsProvider() {
        return ListOfBadCredentials;
    }



    @Test(dataProvider = "badCredentials")
    public void testLoginUser(RegisterData testUser){

        HomePage homePage = BasicLogin(testUser);
        Assert.assertFalse(homePage.isLoggedIn(),
                "ERROR: allows incorrect login");

    }

}
