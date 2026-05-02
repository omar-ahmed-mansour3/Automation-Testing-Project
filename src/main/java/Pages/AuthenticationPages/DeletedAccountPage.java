package Pages.AuthenticationPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletedAccountPage extends BasePage {


    public DeletedAccountPage(WebDriver driver) {
        super(driver);
    }
    //************************************LOCATORS******************************
    private final By DeletedAccountMessage =
        By.cssSelector(".title.text-center >b");


    //***************************METHODS***************************************

    public boolean IsDeleted(){

        Edges_explicitWait(DeletedAccountMessage, 5); // check presence
        return Edges_isDisplayed(DeletedAccountMessage); // check visibility
    }

}
