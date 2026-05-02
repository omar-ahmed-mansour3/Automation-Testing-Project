package Tests.ProductsTests;

import Pages.HomePage;
import Pages.ProductPages.ProductsPage;
import Pages.ProductPages.SearchedProductsPage;
import Tests.BaseTests.BaseTest;
import Utils.JsonDataTypes.ProductsData;
import Utils.jsonHelpers.ProductsDataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class SearchProductTest extends BaseTest {

    static ProductsData[] productsDataList;

    @BeforeClass
    public void setUpMethod() throws FileNotFoundException {
        productsDataList =
                ProductsDataHelper.ReadProductsData("ProductsData.json");
    }

    @DataProvider(name = "ProductsList")
    public Object[] productsListProvider(){
        return productsDataList;
    }

    @Test(dataProvider = "ProductsList")
    public void searchTest(ProductsData testProduct){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: homepage not visible");

        ProductsPage productsPage = homePage.clickOnProductsButton();
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(),
                "ERROR: product's page title not visible");

        SearchedProductsPage searchedProductsPage = productsPage.searchItems(testProduct);
        Assert.assertTrue(searchedProductsPage.isSearchedProductsVisible(),
                "ERROR 'SEARCHED PRODUCT' TITLE NOT VISIBLE");

        List<String> searchedResults = searchedProductsPage.getSearchedProductNames();

        Assert.assertFalse(searchedResults.isEmpty(), "ERROR: No products found!");

        for (String productName : searchedResults) {
            Assert.assertTrue(productName.toLowerCase().contains(testProduct.name.toLowerCase()),
                    "ERROR: Found irrelevant product. Expected '" + testProduct.name +
                            "' but found '" + productName + "'");
        }


    }


}
