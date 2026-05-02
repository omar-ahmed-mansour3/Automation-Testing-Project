package Tests.AuthenticationTests;

import Pages.AuthenticationPages.DeletedAccountPage;
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

public class PositiveLoginTest extends BaseTest {

    static RegisterData[] ListOfRegisteringCredentials;

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        ListOfRegisteringCredentials = RegisterUserHelper.ReadRegisterData("SignUpData.json");
    }

    // This creates a fresh account in the DB before the test runs
    @BeforeMethod
    public void registerUserForTest(Object[] testArgs){
        // We cast the data provider input to RegisterData
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
        // delete and check
        DeletedAccountPage deletedAccountPage = homePage.clickOnDeleteAccountButton();
        Assert.assertTrue(deletedAccountPage.IsDeleted());


    }

}
