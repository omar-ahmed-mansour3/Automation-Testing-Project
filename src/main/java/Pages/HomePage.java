package Pages;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.AuthenticationPages.LoginSignupPage;
import Pages.CartRelatedPages.CartPage;
import Pages.ProductPages.ProductDetailPage;
import Pages.ProductPages.ProductsPage;
import Utils.JsonDataTypes.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // **********************LOCATORS*******************************************************//

    private final By signupLoginButtonLocator = By.cssSelector("[href='/login']");
    private final By loggedInAsLocator = By.cssSelector(".fa.fa-user");
    //********HEADERS**********
    private final By homePageLogoLocator = By.cssSelector("img[alt='Website for automation practice']");
    private final By deleteAccountButtonLocator = By.cssSelector("[href='/delete_account']");
    private final By logoutButtonLocator = By.cssSelector("[href='/logout']");
    private final By contactUsButtonLocator = By.cssSelector("[href='/contact_us']");
    private final By testCasesButtonLocator = By.cssSelector("ul [href='/test_cases']");
    private final By productsButtonLocator = By.cssSelector("ul [href='/products']");
    private final By cartButton = By.cssSelector("#header [href='/view_cart']");

    //*****subscription related locators
    private final By subscriptionTextLocator = By.cssSelector(".single-widget>h2");
    private final By emailTextBoxLocator = By.id("susbscribe_email");
    private final By enterEmailButton = By.id("subscribe");
    private final By successMessageLocator = By.cssSelector(".alert-success.alert");

    //*********** the full fledged text (3 slides)
    private final By fullFledgedText1 = By.cssSelector("#slider-carousel .item:nth-child(1) h2");
    private final By fullFledgedText2 = By.cssSelector("#slider-carousel .item:nth-child(2) h2");
    private final By fullFledgedText3 = By.cssSelector("#slider-carousel .item:nth-child(3) h2");
    private final By scrollUpButton = By.id("scrollUp");

    //*************adding to cart related locators
    // DYNAMIC LOCATOR STRINGS (We use %d instead  of the number)
    private final String verifyProductButtonLocatorTemplate =
            ".features_items [href='/product_details/%d']";
    private final String addToCartHoverLocatorTemplate =
            ".features_items .productinfo.text-center [data-product-id='%d']";
    private final String addToCartClickLocatorTemplate =
            ".features_items .overlay-content [data-product-id='%d']";
    private final By continueShoppingButtonLocator = By.cssSelector(".btn.btn-success.close-modal.btn-block");
    private final By ViewCartButtonLocator =
            By.cssSelector(".text-center [href='/view_cart']");
    private final By successOverLayLocator = By.cssSelector(".modal-content");

    private final String ProductNameLocatorTemplate =
            "//div[contains(@class, 'features_items')]" + // accounts for the product NOT from recommended list
                    "//div[contains(@class, 'productinfo')]//a[@data-product-id='%d']/parent::div/p";

    //***************************recommended items locators
    private final By recommendedItemsTitle = By.cssSelector(".recommended_items .title");
    private final By recommendedAddToCartButtons = By.cssSelector(".recommended_items .add-to-cart");
    private final By recommendedProductNames = By.cssSelector(".recommended_items .productinfo p");

    //*************** NEW LOCATORS FOR CATEGORY SIDEBAR
    private final By categorySidebarLocator = By.id("accordian"); // Left sidebar container
    private final By womenCategoryLocator = By.cssSelector("a[href='#Women']");
    private final By womenTopsLocator = By.cssSelector("a[href='/category_products/1']");


    //*********************METHODS********************************************************//
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************

    //*************************** HELPER METHOD **************

    private By stringToCssLocator(String cssLocator, int productId) {
        if (productId < 1) {
            throw new IllegalArgumentException
                    ("Product ID must be 1 or greater. You passed: " + productId);
        }
        // String.format replaces %d with the number you give it
        return By.cssSelector(String.format(cssLocator, productId));
    }

    private By stringToXpathLocator(String xpathTemplate, int productId) {
        if (productId < 1) {
            throw new IllegalArgumentException
                    ("Product ID must be 1 or greater. You passed: " + productId);
        }
        // String.format replaces %d with the number you give it
        return By.xpath(String.format(xpathTemplate, productId));
    }

    //****************************GETTERS*******************************
    public String getProductNameInHomePage(int id){
        return Edges_getText(stringToXpathLocator(ProductNameLocatorTemplate, id));
    }

    //***********************ACTIONS************
    //**************************************************************************************
    //**************************************************************************************

    public LoginSignupPage click_login_or_signup (){ //click on the login button
        Edges_explicitWait(signupLoginButtonLocator,4);
        Edges_click(signupLoginButtonLocator);
        return new LoginSignupPage(driver);
    }

    public DeletedAccountPage clickOnDeleteAccountButton(){ //click on the delete button
        Edges_click(deleteAccountButtonLocator);
        return new DeletedAccountPage(driver);
    }

    public LoginSignupPage clickLogoutButton(){

        Edges_click(logoutButtonLocator);
        return new LoginSignupPage(driver);
    }
//
//    public ContactUsPage clickOnContactUsButton() {
//        Edges_click(contactUsButtonLocator);
//        return new ContactUsPage(driver);
//    }
    public ProductsPage clickOnProductsButton(){
        Edges_explicitWait(productsButtonLocator,4); // to account for falkiness/ page loading
        Edges_click(productsButtonLocator);
        return new ProductsPage(driver);

    }

    public TestCasesPage clickOnTestCasesButton(){
        Edges_click(testCasesButtonLocator);
        return new TestCasesPage(driver);
    }

    public HomePage fillEmail(RegisterData testUser){
        Edges_sendKeys(emailTextBoxLocator, testUser.email);
        return  this;
    }

    public HomePage clickOnSubscribeButton(){
        Edges_click(enterEmailButton);
        return this;
    }

    public CartPage clickOnCart(){
        Edges_click(cartButton);
        return  new CartPage(driver);
    }

    //scrolling
    public HomePage scrollTillSubscribeIsOnScreen(){
        Edges_scrollToElement(subscriptionTextLocator);
        return this;
    }
    public HomePage clickOnGoToTopButton(){
        Edges_click(scrollUpButton);
        return this;
    }
    public HomePage scrollTOTop(){
        Edges_scrollToElement(homePageLogoLocator);
        return this;
    }

    //adding elements to cart
    public ProductDetailPage clickOnViewProductButton(int productId){
        By locator = stringToCssLocator(verifyProductButtonLocatorTemplate, productId);

        Edges_explicitWait(locator, 2);
        Edges_scrollToElement(locator);
        Edges_click(locator);
        return new ProductDetailPage(driver);
    }

    public HomePage hoverOverProduct(int productId){
        By locator = stringToCssLocator(addToCartHoverLocatorTemplate, productId);

        Edges_explicitWait(locator, 2);
        Edges_scrollToElement(locator);
        Edges_hover(locator);
        return this;
    }

    public HomePage clickOnAddToCartButton(int productId){
        By locator = stringToCssLocator(addToCartClickLocatorTemplate, productId);

        // Remember: NO SCROLL here (it closes the hover menu)
        Edges_explicitWait(locator, 2);
        Edges_click(locator);
        return this;
    }

    public HomePage clickOnContinueShoppingButton(){
        Edges_explicitWait(continueShoppingButtonLocator ,3);
        Edges_click(continueShoppingButtonLocator);
        return this;
    }
    public String addRecommendedProductToCart() {
        // 1. Click the button (Now safely scrolled to center)
        String addedProductName = Edges_clickFirstVisibleAndReturnText(
                recommendedAddToCartButtons,
                recommendedProductNames
        );

        // 2. CRITICAL FIX: Wait for the "Added!" modal to appear
        // This ensures the backend has actually processed the addition before we navigate away
        if(isSuccessOverLayVisible()){
            System.out.println("Success Overlay appeared. Product added confirmed.");
        } else {
            System.out.println("WARNING: Success Overlay NOT visible. Click might have failed.");
        }

        return addedProductName;
    }
    public HomePage clickWomenCategory() {
        Edges_scrollToElement(categorySidebarLocator);
        Edges_click(womenCategoryLocator);
        return this;
    }

    public CategoryProductsPage clickWomenTops() {
        Edges_click(womenTopsLocator);
        return new CategoryProductsPage(driver);
    }

    //*********************CHECKS*****************************************************************
    //**************************************************************************************
    //**************************************************************************************

    public boolean isHomePageVisible() {
        Edges_explicitWait(homePageLogoLocator, 5); // check presence
        return Edges_isDisplayed(homePageLogoLocator); // check visibility

    }
// Inside HomePage.java

    public boolean isLoggedIn() {
        //without the try catch block, the test will fail/crash if the use is not logged in
        //instead of gracefully returning FALSE
        try {
            Edges_explicitWait(loggedInAsLocator, 2);
            return Edges_isDisplayed(loggedInAsLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubscriptionTextVisibleAfterScrolling(){

        try{
            Edges_scrollToElement(subscriptionTextLocator);
            return Edges_isDisplayed(subscriptionTextLocator);
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean isSubscriptionSuccessMessageVisible(){
        try {
            Edges_explicitWait(successMessageLocator,3);
            return Edges_isDisplayed(successMessageLocator);
        } catch (Exception e) {
            return false;
        }
    }


public boolean isSubscriptionTextVisibleInViewport(){
    return Edges_isElementInViewport(subscriptionTextLocator);
}

    public boolean isFullFledgedMessageVisibleInViewport(){
        try {
            // We check if ANY of the slide headers are visible in the viewport
            return Edges_isElementInViewport(fullFledgedText1) ||
                    Edges_isElementInViewport(fullFledgedText2) ||
                    Edges_isElementInViewport(fullFledgedText3);
        } catch (Exception e) {
            return false;
        }
    }





    //adding to cart related checks
    // Works for any product ID
    public boolean isButtonOnOverLayVisible(int productId){
        try{
            By locator = stringToCssLocator(addToCartClickLocatorTemplate, productId);
            Edges_explicitWait(locator, 2);
            return Edges_isDisplayed(locator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessOverLayVisible(){
        try{
            Edges_explicitWait(successOverLayLocator, 2);
            return Edges_isDisplayed(successOverLayLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRecommendedItemsVisible() {
        try {
            Edges_scrollToElement(recommendedItemsTitle);
            return Edges_isDisplayed(recommendedItemsTitle);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCategorySidebarVisible() {
        Edges_explicitWait(categorySidebarLocator, 10);
        return driver.findElement(categorySidebarLocator).isDisplayed();
    }
}

