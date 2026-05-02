package Pages.CartRelatedPages;

import Pages.AuthenticationPages.LoginSignupPage;
import Pages.BasePage;
import Utils.JsonDataTypes.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //*******************locators********************************************

    private final By proceedToCheckoutButtonLocator = By.cssSelector(".btn.btn-default.check_out");
    private final By loginOrSignUpOverlayLocator = By.className("modal-content");
    private final By loginButtonOnOverlayLocator = By.cssSelector(".modal-body [href='/login']");

    //*****SUBSCRIPTION
    private final By subscriptionTextLocator = By.cssSelector(".single-widget>h2");
    private final By emailTextBoxLocator = By.id("susbscribe_email");
    private final By enterEmailButton = By.cssSelector("button#subscribe");
    private final By successMessageLocator = By.cssSelector(".alert-success.alert");
    //********ITEMS
    private final By cartProductNamesLocator =
            By.cssSelector(".cart_description h4 a");
    private final String priceLocatorStringTemplate = "#product-%d .cart_price";
    private final String quantityLocatorStringTemplate = "#product-%d .disabled";
    private final String totalPriceLocatorStringTemplate = "#product-%d .cart_total_price";
    private final String removeItemButtonStringTemplate = ".cart_quantity_delete[data-product-id='%d']";
    private final String productIdStringTemplate = "#product-%d";

    //***********************METHODS*************************************

    //**********HELPERS****************
    public List<String> getProductsInCart() {
        List<WebElement> elements = Edges_getWebElements(cartProductNamesLocator);
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    private By stringToCssLocator(String cssLocator, int productId) {
        if (productId < 1) {
            throw new IllegalArgumentException
                    ("Product ID must be 1 or greater. You passed: " + productId);
        }
        // String.format replaces %d with the number you give it
        return By.cssSelector(String.format(cssLocator, productId));
    }

    //**********************GETTERS
    public String getProductPriceInCartPage(int productId){
        By locator = stringToCssLocator(priceLocatorStringTemplate , productId);
        return Edges_getText(locator);
    }

    public String getProductQuantityInCartPAge(int productId){
        By locator = stringToCssLocator(quantityLocatorStringTemplate , productId);
        return Edges_getText(locator);
    }

    public String getProductTotalPriceInCartPage(int productID){
        return Edges_getText
                (stringToCssLocator(totalPriceLocatorStringTemplate,productID));
    }

    //*******************ACTIONS

    public CartPage fillEmail(RegisterData testUser){
        Edges_sendKeys(emailTextBoxLocator, testUser.email);
        return  this;
    }

    public CartPage clickOnSubscribeButton(){
        Edges_click(enterEmailButton);
        return this;
    }

    public CartPage clickOnProceedToCheckoutButtonWhileloggedOut(){
        Edges_click(proceedToCheckoutButtonLocator);
        return  this;
    }
    public CheckoutPage clickOnProceedToCheckoutButtonWhileLoggedIn(){
        Edges_click(proceedToCheckoutButtonLocator);
        return  new CheckoutPage(driver);
    }

    public LoginSignupPage clickOnOverlayLogin(){
        Edges_click(loginButtonOnOverlayLocator);
        return new LoginSignupPage(driver);
    }

    public CartPage clickOnDeleteItemButton(int id){
        Edges_click(stringToCssLocator(removeItemButtonStringTemplate, id));
        return this;
    }

    //************************CHECKS***************************
    public boolean isSubscriptionTextVisibleAfterScrolling(){

        try{
            Edges_scrollToElement(subscriptionTextLocator);
            return Edges_isDisplayed(subscriptionTextLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubscriptionSuccessMessageVisible(){
        try {
            Edges_explicitWait(successMessageLocator,3);
            return Edges_isDisplayed(successMessageLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isProductInCart(String expectedProductName) {
        List<String> currentCartItems = getProductsInCart();
        for (String item : currentCartItems) {
            if (item.equalsIgnoreCase(expectedProductName)) return true;
        }
        return false;
    }
    //overloading , uses int id instead of name
    public boolean isProductInCart(int productId) {
        try {
            By locator = stringToCssLocator(productIdStringTemplate, productId);
            return Edges_isDisplayed(locator);
        } catch (Exception e) {
            return false;
        }
    }
    public void waitTillProductRemoved(int productId) {
        By locator = stringToCssLocator(productIdStringTemplate, productId);
        Edges_waitTillDisappear(locator);
    }
    public boolean isLoginOverlayVisible(){
        try{
            return Edges_isDisplayed(loginOrSignUpOverlayLocator);
        } catch (Exception e) {
            return false;
        }
    }


}
