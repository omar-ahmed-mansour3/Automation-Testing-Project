package Tests.CartTests;

import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Tests.BaseTests.CartTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRecommendedItemsTest extends CartTestBase {


    @Test
    public void addRecommendedItemsTest(){// 1. Launch browser & 2. Navigate (BaseTest)
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "ERROR: Homepage not visible");

        //Scroll to bottom & Verify Title
        // isRecommendedItemsVisible handles the scroll automatically
        Assert.assertTrue(homePage.isRecommendedItemsVisible(),
                "ERROR: 'RECOMMENDED ITEMS' title is not visible");

        //Click on 'Add To Cart' on Recommended product
        String addedProductName = homePage.addRecommendedProductToCart();
        homePage.clickOnContinueShoppingButton();

        //Click on 'View Cart' button
        CartPage cartPage = homePage.clickOnCart();

        // 7. Verify that product is displayed in cart page
        Assert.assertTrue(cartPage.isProductInCart(addedProductName),
                "ERROR: The recommended product '" + addedProductName + "' was NOT found in the cart!");

    }
}
