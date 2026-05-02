package Pages.CartRelatedPages;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentDonePage extends BasePage {

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    // ************************* LOCATORS *************************
    private final By orderPlacedSuccessMsg = By.cssSelector("[data-qa='order-placed']");
    private final By deleteAccountButton = By.cssSelector("a[href='/delete_account']");

    // ************************* METHODS *************************

    //********************ACTIONS
    public DeletedAccountPage clickDeleteAccountButton() {
        Edges_click(deleteAccountButton);
        return new DeletedAccountPage(driver);
    }

    //*******************CHECKS
    public boolean isOrderPlacedSuccessMessageVisible() {
        try {
            Edges_explicitWait(orderPlacedSuccessMsg, 5);
            return Edges_isDisplayed(orderPlacedSuccessMsg);
        } catch (Exception e) {
            return false;
        }
    }
}