package Pages.ProductPages;

import Pages.BasePage;
import Pages.CartRelatedPages.CartPage;
import Utils.JsonDataTypes.ProductsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    //*************************** LOCATORS *********************************************

    private final By allProductsTitleLocator = By.cssSelector(".title.text-center");
    private final By productListLocator = By.cssSelector(".features_items");
    private final By searchBarLocator = By.cssSelector("input#search_product");
    private final By searchButtonLocator = By.cssSelector("button#submit_search");
    private final By continueShoppingButtonLocator = By.cssSelector(".btn.btn-success.close-modal.btn-block");
    private final By ViewCartButtonLocator =
            By.cssSelector(".text-center [href='/view_cart']");

    //BRANDS RELATED LOCATORS
    private final By brandsListLocator = By.className("brands-name");
    private final By poloBrandButtonLocator =
            By.cssSelector("[href='/brand_products/Polo']");





    // DYNAMIC LOCATOR STRINGS (We use %d instead  of the number)
    private final String verifyProductButtonLocatorTemplate = "[href='/product_details/%d']";
    private final String addToCartHoverLocatorTemplate = ".productinfo.text-center [data-product-id='%d']";
    private final String addToCartClickLocatorTemplate = ".overlay-content [data-product-id='%d']";

    // the only way to make those following 2 dynamic, is by using X-path
    private final String ProductNameLocatorTemplate =
            "//div[contains(@class, 'productinfo')]//a[@data-product-id='%d']/parent::div/p";
    private final String ProductPriceLocatorTemplate =
            "//div[contains(@class, 'productinfo')]//a[@data-product-id='%d']/parent::div/h2";
    private final By successOverLayLocator = By.cssSelector(".modal-content");



    //*********************************METHODS********************************************

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

    //******************************GETTERS*****************

    public String getProductNameInProductsPage(int id){
        return Edges_getText( stringToXpathLocator(ProductNameLocatorTemplate,id));
    }
    public String getProductPriceInProductsPage(int id){
        return Edges_getText( stringToXpathLocator(ProductPriceLocatorTemplate,id));
    }


    //************************ ACTIONS *********
    public ProductDetailPage clickOnViewProductButton(int productId){
        By locator = stringToCssLocator(verifyProductButtonLocatorTemplate, productId);

        Edges_explicitWait(locator, 2);
        Edges_scrollToElement(locator);
        Edges_click(locator);
        return new ProductDetailPage(driver);
    }

    public ProductsPage hoverOverProduct(int productId){
        By locator = stringToCssLocator(addToCartHoverLocatorTemplate, productId);

        Edges_explicitWait(locator, 2);
        Edges_scrollToElement(locator);
        Edges_hover(locator);
        return this;
    }

    public ProductsPage clickOnAddToCartButton(int productId){
        By locator = stringToCssLocator(addToCartClickLocatorTemplate, productId);

        // Remember: NO SCROLL here (it closes the hover menu)
        Edges_explicitWait(locator, 2);
        Edges_click(locator);
        return this;
    }

    public ProductsPage clickOnContinueShoppingButton(){
        Edges_explicitWait(continueShoppingButtonLocator ,3);
        Edges_click(continueShoppingButtonLocator);
        return this;
    }

    public SearchedProductsPage searchItems(ProductsData productsData){
        Edges_sendKeys(searchBarLocator,productsData.name);
        Edges_click(searchButtonLocator);
        return new SearchedProductsPage(driver);
    }

    public CartPage clickOnViewCartButton(){
        Edges_explicitWait(ViewCartButtonLocator,2);
        Edges_click(ViewCartButtonLocator);
        return new CartPage(driver);
    }

    public PoloBrandPage clickOnPoloBrand(){
        Edges_scrollToElement(poloBrandButtonLocator);
        Edges_click(poloBrandButtonLocator);
        return new PoloBrandPage(driver);
    }

    //**************** CHECKS ***************

    public boolean isAllProductsTitleVisible(){
        Edges_explicitWait(allProductsTitleLocator,5);
        return Edges_isDisplayed(allProductsTitleLocator);
    }

    public boolean isProductListVisible(){
        Edges_explicitWait(productListLocator,5);
        return Edges_isDisplayed(productListLocator);
    }

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

    public boolean isBrandListVisible(){
        try{
            Edges_explicitWait(brandsListLocator, 2);
            return Edges_isDisplayed(brandsListLocator);
        } catch (Exception e) {
            return false;
        }
    }
}