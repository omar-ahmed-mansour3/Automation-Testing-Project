package Tests.CartTests;

import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Pages.ProductPages.ProductsPage;
import Tests.BaseTests.CartTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductsInCartTest extends CartTestBase {


    @Test
    public void AddProductsToCartTest(){
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickOnProductsButton();

        //saving the names and prices for future comparison when they are in cart
        String prod1Name = productsPage.getProductNameInProductsPage(1);
        String prod1Price = productsPage.getProductPriceInProductsPage(1);
        String prod1Quantity = "1";

        String prod2Name = productsPage.getProductNameInProductsPage(2);
        String prod2Price = productsPage.getProductPriceInProductsPage(2);
        String prod2Quantity = "1";

        //**************add first one to cart
        //use parent class method
        addProductFromProductsPage(productsPage,1,1);
        productsPage.clickOnContinueShoppingButton();

        //**************************add 2nd product to cart
        //use parent class method
        addProductFromProductsPage(productsPage,2,1);
        CartPage cartPage = productsPage.clickOnViewCartButton();


        //********************CHECKING ITEM 1
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


        //********************CHECKING ITEM 2
        Assert.assertTrue(cartPage.isProductInCart(prod2Name),
                "ERROR:" + prod2Name + "was not found in the cart!");


        Assert.assertEquals(cartPage.getProductPriceInCartPage(2), prod2Price,
                "ERROR:" + prod2Name + " Price is incorrect!");

        Assert.assertEquals(cartPage.getProductQuantityInCartPAge(2), prod2Quantity,
                "ERROR:" + prod2Name + " Quantity is incorrect!");

        Assert.assertEquals(cartPage.getProductTotalPriceInCartPage(2),
                calculateExpectedTotal(prod2Price,prod2Quantity),
                "ERROR:" + prod2Name + " Total is incorrect!");

    }

}
