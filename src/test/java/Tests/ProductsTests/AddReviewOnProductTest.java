package Tests.ProductsTests;

import Pages.HomePage;
import Pages.ProductPages.ProductDetailPage;
import Pages.ProductPages.ProductsPage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class AddReviewOnProductTest extends BaseTest {

    static RegisterData[] UserData;

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        UserData =
                RegisterUserHelper.ReadRegisterData("SignUpData.json");
    }

    @DataProvider(name = "testData")
    public Object[] userCredentialsProvider() {
        return UserData;
    }

    @Test(dataProvider =  "testData")
    public void addReviewOnProductTest(RegisterData testUser){
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickOnProductsButton();
        Assert.assertTrue(productsPage.isAllProductsTitleVisible() ,
                "ERROR : All Products Title IS NOT Visible");

        ProductDetailPage productDetailPage =
                productsPage.clickOnViewProductButton(1);

        Assert.assertTrue(productDetailPage.isWriteReviewVisible() ,
                "ERROR: WRITE YOUR REVIEW NOT VISIBLE");
        //make sure the message is NOT ALWAYS displayed
        Assert.assertFalse(productDetailPage.isReviewSubmittedSuccessfullyMessageVisible(),
        "ERROR :message appears even before we submit review");

        productDetailPage.fillName(testUser)
            .fillEmail(testUser)
                .fillReview()
                    .clickSubmitReview();
        Assert.assertTrue(productDetailPage.isReviewSubmittedSuccessfullyMessageVisible()
        ,"ERROR: SUCCESS MESSAGE IS NOT VISIBLE");


    }
}
