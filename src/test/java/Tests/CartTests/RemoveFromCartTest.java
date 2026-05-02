package Tests.CartTests;

import Pages.CartRelatedPages.CartPage;
import Pages.HomePage;
import Tests.BaseTests.CartTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveFromCartTest extends CartTestBase {

    @Test
    public void removeFromCartTest() throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: homepage not visible");
        //*************ADD PRODUCTS********************
        int prod1Id = 1;
        int quantity =1;
        int prod2Id = 2;

        // add 2 different products
        addProductFromHomePage(homePage,prod1Id,quantity);
        homePage.clickOnContinueShoppingButton();
        addProductFromHomePage(homePage,prod2Id,1);
        homePage.clickOnContinueShoppingButton();
        //save products names for further assertions
        String prod1Name = homePage.getProductNameInHomePage(prod1Id);
        String prod2Name = homePage.getProductNameInHomePage(prod2Id);

        //******************go to cart*******************
        CartPage cartPage = homePage.clickOnCart();
        //***************CHECK THE 2 PRODUCTS ARE ADDED******************
        Assert.assertTrue(cartPage.isProductInCart(prod1Name),
                "ERROR: THE ADDED PRODUCT " +prod1Name +" NOT FOUND");
        Assert.assertTrue(cartPage.isProductInCart(prod2Name),
                "ERROR: THE ADDED PRODUCT " +prod2Name +" NOT FOUND");

        //*************DELETING ITEM 1 *************************
        cartPage.clickOnDeleteItemButton(prod1Id);
        //wait till element disappears
        cartPage.waitTillProductRemoved(prod1Id);
        Assert.assertFalse(cartPage.isProductInCart(prod1Id),"ERROR: ELEMENT " +prod1Name +" IS NOT DELETED");
        //*************DELETING ITEM 2 *************************
        cartPage.clickOnDeleteItemButton(prod2Id);
        cartPage.waitTillProductRemoved(prod2Id);
        Assert.assertFalse(cartPage.isProductInCart(prod2Id),"ERROR: ELEMENT " +prod1Name +"  IS NOT DELETED");


    }
}
