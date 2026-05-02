package Tests.BaseTests;

import Pages.HomePage;
import Pages.ProductPages.ProductsPage;
import org.testng.Assert;

abstract public class CartTestBase extends BaseTest {

    // Helper to calculate "Price * Quantity" and return formatted string "Rs. 500"
    public String calculateExpectedTotal(String price, String quantity) {

        //removes any characters and only leaves letters
        String cleanPrice = price.replaceAll("[^0-9]", "");
        // string to int
        int priceInt = Integer.parseInt(cleanPrice);
        //is already clean, so we only need to (string to int)
        int quantityInt = Integer.parseInt(quantity);

        int totalInt = priceInt * quantityInt;

        // the format of the price comes with the RS. similar to the $.
        //we removed it in the first line, now we are putting it back
        // to be able to compare it to the text in the HTML

        // tldr:  Put the "Rs. " back
        return "Rs. " + totalInt;
    }


    protected void addProductFromHomePage(HomePage homePage, int productId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            homePage.hoverOverProduct(productId);
            Assert.assertTrue(homePage.isButtonOnOverLayVisible(productId),
                    "ERROR: Overlay not visible (Home)");

            homePage.clickOnAddToCartButton(productId);
            Assert.assertTrue(homePage.isSuccessOverLayVisible(),
                    "ERROR: Success overlay not visible");

            if(i == quantity-1 ) continue;
            homePage.clickOnContinueShoppingButton();
        }
    }

    protected void addProductFromProductsPage(ProductsPage productsPage, int productId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            productsPage.hoverOverProduct(productId);
            Assert.assertTrue(productsPage.isButtonOnOverLayVisible(productId),
                    "ERROR: Overlay not visible (Product Page)");

            productsPage.clickOnAddToCartButton(productId);
            Assert.assertTrue(productsPage.isSuccessOverLayVisible(),
                    "ERROR: Success overlay not visible");
            if(i == quantity-1 ) continue;
            productsPage.clickOnContinueShoppingButton(); // Always close for stability
        }
    }

}
