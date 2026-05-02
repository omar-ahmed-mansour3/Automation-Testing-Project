package Pages.ProductPages;

import Pages.BasePage;
import Utils.JsonDataTypes.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }
    //*************************LOCATORS****************************************
    private final By ProductNameLocator = By.cssSelector(".product-information>h2");
    private final By categoryLocator =
             By.xpath("//div[@class='product-information']//p[contains(text(), 'Category:')]");

    private final By priceLocator =
            By.xpath("//div[@class='product-information']//span[contains(text(), 'Rs.')]");

    private final By availabilityLocator =
            By.xpath("//div[@class='product-information']//b[contains(text(), 'Availability:')]");

    private final By conditionLocator =
            By.xpath("//div[@class='product-information']//b[contains(text(), 'Condition:')]");

    private final By brandLocator =
            By.xpath("//div[@class='product-information']//b[contains(text(), 'Brand:')]");

    //review related locators
    private final By writeReviewMessageLocator = By.cssSelector("[href='#reviews']");
    private final By nameTextFieldLocator = By.id("name");
    private final By emailTextFieldLocator = By.id("email");
    private final By reviewTextFieldLocator = By.cssSelector("[name='review']");
    private final By submitReviewButton = By.id("button-review");
    private final By reviewSubmittedSuccessfully =
            By.cssSelector(".alert-success.alert>span");

    //*************************METHODS************************************

    //********************ACTIONS

    public ProductDetailPage fillName(RegisterData user){
        Edges_scrollToElement(nameTextFieldLocator);
        Edges_sendKeys(nameTextFieldLocator, user.name);
        return this;
    }
    public ProductDetailPage fillEmail(RegisterData user){
        Edges_scrollToElement(emailTextFieldLocator);
        Edges_sendKeys(emailTextFieldLocator, user.email);
        return this;
    }
    public ProductDetailPage fillReview(){
        Edges_scrollToElement(reviewTextFieldLocator);
        Edges_sendKeys(reviewTextFieldLocator,"bla bla bla 123 @ % _ $");
        return this;
    }
    public ProductDetailPage clickSubmitReview(){
        Edges_scrollToElement(submitReviewButton);
        Edges_click(submitReviewButton);
        return this;
    }

    //********************CHECKS

    public boolean isCategoryVisible(){
        try {
            Edges_explicitWait(categoryLocator, 5);
            return Edges_isDisplayed(categoryLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPriceVisible(){
        try {
            Edges_explicitWait(priceLocator, 5);
            return Edges_isDisplayed(priceLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isAvailabilityVisible(){
        try {
            Edges_explicitWait(availabilityLocator, 5);
            return Edges_isDisplayed(availabilityLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isConditionVisible(){
        try {
            Edges_explicitWait(conditionLocator, 5);
            return Edges_isDisplayed(conditionLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isBrandVisible(){
        try {
            Edges_explicitWait(brandLocator, 5);
            return Edges_isDisplayed(brandLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isWriteReviewVisible(){
        try{
            Edges_explicitWait(writeReviewMessageLocator, 3);
            return Edges_isDisplayed(writeReviewMessageLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isReviewSubmittedSuccessfullyMessageVisible(){
        try{
            Edges_explicitWait(reviewSubmittedSuccessfully,5);
            return Edges_isDisplayed(reviewSubmittedSuccessfully);
        } catch (Exception e) {
            return false;
        }
    }

}
