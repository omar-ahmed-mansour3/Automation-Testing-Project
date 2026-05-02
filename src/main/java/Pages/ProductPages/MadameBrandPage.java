package Pages.ProductPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MadameBrandPage extends BasePage {
    public MadameBrandPage(WebDriver driver) {
        super(driver);
    }
    //**********************LOCATORS***************************************
    private final By brandMadameTitleLocator = By.cssSelector(".title.text-center");
    private final By productListLocator = By.className("features_items");
    private final By BrandListLocator =By.className("brands-name");
    private final By poloBrandButtonLocator =
            By.cssSelector("[href='/brand_products/Polo']");

    //***************************************METHODS*************************************

    //***********************ACTIONS*******************
    public PoloBrandPage clickOnMadameBrandButton(){
        Edges_scrollToElement(poloBrandButtonLocator);
        Edges_click(poloBrandButtonLocator);
        return new PoloBrandPage(driver);
    }


    //***********************CHECKS*******************
    public boolean isMadameBrandTitleVisible(){
        try{
            Edges_explicitWait(brandMadameTitleLocator, 2);
            return Edges_isDisplayed(brandMadameTitleLocator);
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
    public boolean isMadameProductsListVisible(){
        try{
            Edges_explicitWait(productListLocator, 2);
            return Edges_isDisplayed(productListLocator);
        } catch (Exception e) {
            return false;
        }
    }

}
