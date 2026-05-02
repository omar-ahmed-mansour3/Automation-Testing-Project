package Pages.ProductPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PoloBrandPage extends BasePage {
    public PoloBrandPage(WebDriver driver) {
        super(driver);
    }

    //************************************LOCATORS***************************************
    private final By brandPoloTitleLocator = By.cssSelector(".title.text-center");
    private final By BrandListLocator =By.className("brands-name");
    private final By productListLocator = By.className("features_items");
    private final By madameBrandButtonLocator =
            By.cssSelector("[href='/brand_products/Madame']");
    //***************************************METHODS*************************************

    //***********************ACTIONS*******************
    public MadameBrandPage clickOnMadameBrandButton(){
        Edges_scrollToElement(madameBrandButtonLocator);
        Edges_click(madameBrandButtonLocator);
        return new MadameBrandPage(driver);
    }


    //***********************CHECKS*******************
    public boolean isPoloBrandTitleVisible(){
        try{
            Edges_explicitWait(brandPoloTitleLocator, 2);
            return Edges_isDisplayed(brandPoloTitleLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isBrandListVisible(){
        try{
            Edges_explicitWait(BrandListLocator, 2);
            return Edges_isDisplayed(BrandListLocator);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPoloProductsListVisible(){
        try{
            Edges_explicitWait(productListLocator, 2);
            return Edges_isDisplayed(productListLocator);
        } catch (Exception e) {
            return false;
        }
    }


}
