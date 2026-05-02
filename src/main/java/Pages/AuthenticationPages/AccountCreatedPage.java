package Pages.AuthenticationPages;

import Pages.BasePage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    //***************************LOCATORS******************************************
    private final By accountCreatedMessageLocator =
            By.cssSelector(".title.text-center>b");
    private final By continueButtonLocator =
            By.cssSelector(".btn.btn-primary");


    //********************METHODS*********************
    //**************ACTIONS
    public HomePage clickOnContinueButton(){
        Edges_click(continueButtonLocator);
        return new HomePage(driver);
    }

    //**************CHECKS
    public boolean isMessageCreatedTitleVisible(){
        Edges_explicitWait(accountCreatedMessageLocator,5);
        return Edges_isDisplayed(accountCreatedMessageLocator);
    }


}
