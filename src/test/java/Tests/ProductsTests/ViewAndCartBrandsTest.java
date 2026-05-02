package Tests.ProductsTests;

import Pages.HomePage;
import Pages.ProductPages.MadameBrandPage;
import Pages.ProductPages.PoloBrandPage;
import Pages.ProductPages.ProductsPage;
import Tests.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewAndCartBrandsTest extends BaseTest {

    @Test
    public void viewAndCartBrandsTest()
    {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickOnProductsButton();
        Assert.assertTrue(productsPage.isBrandListVisible() ,
                "ERROR: BRAND LIST NOT VISIBLE");

        PoloBrandPage poloBrandPage = productsPage.clickOnPoloBrand();

        Assert.assertTrue(poloBrandPage.isPoloProductsListVisible() ,
                "ERROR POLO TITLE NOT VISIBLE");
        Assert.assertTrue(poloBrandPage.isPoloBrandTitleVisible() ,
                "ERROR POLO products NOT VISIBLE");
        Assert.assertTrue(poloBrandPage.isBrandListVisible() ,
                "ERROR BRAND LIST NOT VISIBLE");

        MadameBrandPage madameBrandPage = poloBrandPage.clickOnMadameBrandButton();

        Assert.assertTrue(madameBrandPage.isMadameBrandTitleVisible() ,
                "ERROR Madame TITLE NOT VISIBLE");
        Assert.assertTrue(madameBrandPage.isMadameProductsListVisible(),
                "ERROR Madame products NOT VISIBLE");
        Assert.assertTrue(madameBrandPage.isBrandListVisible() ,
                "ERROR BRAND LIST NOT VISIBLE");

    }




}
