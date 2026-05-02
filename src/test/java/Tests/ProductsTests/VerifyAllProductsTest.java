package Tests.ProductsTests;

import Pages.HomePage;
import Pages.ProductPages.ProductDetailPage;
import Pages.ProductPages.ProductsPage;
import Tests.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAllProductsTest extends BaseTest {

    @Test
    public void verifyAllProducts(){
        HomePage homePage = new HomePage(driver);
        homePage.isHomePageVisible();

        ProductsPage productsPage = homePage.clickOnProductsButton();
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(),
                "ERROR: title 'All Products' is not visible");
        Assert.assertTrue(productsPage.isProductListVisible(),
                "ERROR: 'Product list not visible'");

        ProductDetailPage productDetailPAge = productsPage.clickOnViewProductButton(1);

        Assert.assertTrue(productDetailPAge.isBrandVisible(),
                "ERROR: BRAND NOT VISIBLE");

        Assert.assertTrue(productDetailPAge.isConditionVisible(),
                "ERROR: condition NOT VISIBLE");
        Assert.assertTrue(productDetailPAge.isAvailabilityVisible(),
                "ERROR: Availability NOT VISIBLE");
        Assert.assertTrue(productDetailPAge.isCategoryVisible(),
                "ERROR: category NOT VISIBLE");
        Assert.assertTrue(productDetailPAge.isPriceVisible(),
                "ERROR: PRICE NOT VISIBLE");
    }


}
