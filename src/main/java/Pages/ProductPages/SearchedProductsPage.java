package Pages.ProductPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchedProductsPage extends BasePage {
    public SearchedProductsPage(WebDriver driver) {
        super(driver);
    }

    //******************************LOCATORS*********************************
    private final By searchedProductsTitleLocator =
            By.cssSelector(".title.text-center");

    private final By allProductNamesLocator = By.cssSelector(".productinfo p");
    //****************************METHODS***********************************

    //****************GETTERS
    public List<String> getSearchedProductNames() {

        //would this be considered as using selenium in the project?
        // what other alternatives are there
        List<WebElement> elements = Edges_getWebElements(allProductNamesLocator);
        List<String> names = new ArrayList<>();

        for (WebElement element : elements) {
            names.add(element.getText());
        }

        return names;
    }

    public List<Integer> getSearchedProductIDs() {
        // Locator for all "Add to Cart" buttons
        By allAddToCartButtons = By.cssSelector(".features_items .productinfo.text-center a.add-to-cart");

        // This returns List<String> directly
        List<String> idStrings = Edges_getListOfAttributes(allAddToCartButtons, "data-product-id");

        List<Integer> ids = new ArrayList<>();

        //loop on string and transform to intger
        for (String s : idStrings) {
            if (s != null) {
                ids.add(Integer.parseInt(s));
            }
        }
        return ids;
    }
    //****************ACTIONS

    //**************CHECKS

    public boolean isSearchedProductsVisible(){
        try {
            Edges_explicitWait(searchedProductsTitleLocator,5);
            return Edges_isDisplayed(searchedProductsTitleLocator);
        } catch (Exception e){
            return false;
        }

    }

}
