package Tests.ProductsTests;

import Pages.AuthenticationPages.LoginSignupPage;
import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Pages.ProductPages.ProductsPage;
import Pages.ProductPages.SearchedProductsPage;
import Tests.BaseTests.CartTestBase;
import Utils.JsonDataTypes.ProductsData;
import Utils.JsonDataTypes.RegisterData;
import Utils.jsonHelpers.ProductsDataHelper;
import Utils.jsonHelpers.RegisterUserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class SearchProductAndVerifyCartTest extends CartTestBase {

    static ProductsData[] productsDataList;
    //we don't need an array, just one user is enough
    static RegisterData validRegisterData;
    @DataProvider(name = "ProductsList")
    public Object[] productsListProvider(){
        return productsDataList;
    }

    @BeforeClass
    public void setUpClass() throws FileNotFoundException {
        productsDataList = ProductsDataHelper.ReadProductsData("ProductsData.json");
        //Load ONE user from SignUpData.json to use as our "Test User"
        validRegisterData = RegisterUserHelper.ReadRegisterData("SignUpData.json")[0];
    }

    //this signs up the user so that he is able to log in
    // there is an after method in BaseTest that deletes the account
    @BeforeMethod
    public void createPreRequisiteAccount() {
        // This inherited method registers the user
        registerUser(validRegisterData);
    }

    @Test(dataProvider = "ProductsList")
    public void searchProductAndVerifyCartTest(ProductsData testProduct){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: homepage not visible");

        //******************GO TO PRODUCTS PAGE************************
        ProductsPage productsPage = homePage.clickOnProductsButton();
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(),
                "ERROR: product's page title not visible");

        //*******************GO TO SEARCHED PRODUCTS PAGE**************************
        SearchedProductsPage searchedProductsPage = productsPage.searchItems(testProduct);
        Assert.assertTrue(searchedProductsPage.isSearchedProductsVisible(),
                "ERROR 'SEARCHED PRODUCT' TITLE NOT VISIBLE");


        //*******FILL ARRAY/VECTOR WITH THE SEARCHED PRODUCTS*********
        List<String> searchedNames = searchedProductsPage.getSearchedProductNames();
        Assert.assertFalse(searchedNames.isEmpty(), "ERROR: No products found!");


        //*******CHECK THAT ONLY SEARCHED PRODUCTS ARE VISIBLE******************
        for (String productName : searchedNames) {
            Assert.assertTrue(productName.toLowerCase().contains(testProduct.name.toLowerCase()),
                    "ERROR: Found irrelevant product. Expected '" + testProduct.name +
                            "' but found '" + productName + "'");
        }

        //******************ADDING ALL PRODUCTS TO CART************************
        //save all id's in this list
        List<Integer> foundIds = searchedProductsPage.getSearchedProductIDs();

        for (int id : foundIds) {
            // while we are NOT in the product page, it shares the same locators
            //so we can just use the productPage method
            addProductFromProductsPage(productsPage, id, 1);
        }

        //**********************GO TO CART**********************************
        CartPage cartPage = productsPage.clickOnViewCartButton();

        //check that we added ALL the products
        for (String productName : searchedNames) {
            Assert.assertTrue(cartPage.isProductInCart(productName),
                    "ERROR: Product '" + productName + "' was not found in the cart before login.");
        }

        //*********login with
        LoginSignupPage loginPage = homePage.click_login_or_signup();
        loginPage.login_enter_email_address(validRegisterData.email);
        loginPage.login_enter_password(validRegisterData.password);
        homePage = loginPage.login_click_login();
        Assert.assertTrue(homePage.isLoggedIn(), "ERROR: Failed to log in during test.");

        //***************************GO TO CART AGAIN***************
        cartPage = homePage.clickOnCart();

        //*******************CHECK THAT THE PROGRESS IS STILL SAVED AFTER LOGGING IN********************
        for (String productName : searchedNames) {
            Assert.assertTrue(cartPage.isProductInCart(productName),
                    "ERROR: Product '" + productName + "' disappeared from cart after login!");
        }


    }

}
