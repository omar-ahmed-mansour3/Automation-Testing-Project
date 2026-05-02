package Tests.CartTests;

import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Pages.ProductPages.ProductsPage;
import Tests.BaseTests.CartTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyProductQuantityInCartTest extends CartTestBase {

    @Test
    public void verifyProductQuantity(){
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickOnProductsButton();
        //saving the names and prices for future comparison when they are in cart
        String prod1Name = productsPage.getProductNameInProductsPage(1);
        String prod1Price = productsPage.getProductPriceInProductsPage(1);
        String prod1Quantity = "4";


        // using the method in the parent class CartBaseTest to add the products
        addProductFromProductsPage(productsPage,1,4);

        CartPage cartPage = productsPage.clickOnViewCartButton();

        Assert.assertTrue(cartPage.isProductInCart(prod1Name),
                "ERROR:" + prod1Name + "was not found in the cart!");
        Assert.assertEquals(cartPage.getProductPriceInCartPage(1), prod1Price,
                "ERROR:" + prod1Name + "Price is incorrect!");
        // Check Quantity
        Assert.assertEquals(cartPage.getProductQuantityInCartPAge(1), prod1Quantity,
                "ERROR:" + prod1Name + " Quantity is incorrect!");

        Assert.assertEquals(cartPage.getProductTotalPriceInCartPage(1),
                calculateExpectedTotal(prod1Price,prod1Quantity),
                "ERROR:" + prod1Name + "Total is incorrect!");
    }



}
