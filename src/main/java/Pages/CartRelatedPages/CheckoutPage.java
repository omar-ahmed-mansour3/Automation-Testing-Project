package Pages.CartRelatedPages;

import Pages.AuthenticationPages.DeletedAccountPage;
import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ************************************* LOCATORS ******************************************

    //  ADDRESS BLOCKS
    private final By deliveryAddressBlock = By.id("address_delivery");
    private final By billingAddressBlock = By.id("address_invoice");


    //  ACTION AREA
    private final By commentTextArea = By.cssSelector("textarea[name='message']");
    private final By placeOrderButton = By.cssSelector("a[href='/payment']");

    private final By deleteAccountButtonLocator = By.cssSelector("[href='/delete_account']");


    // ************************************* METHODS ******************************************

    // ************ ADDRESS VERIFICATION ************

    /**
     * instead of checking each and every line using it is class locator, to check the text
     * it's much easier to just get ALL the text, and check it using "contains"
     */
    public boolean verifyDeliveryAddressContains(String textToFind) {
        try {
            Edges_explicitWait(deliveryAddressBlock, 5);
            String fullText = Edges_getText(deliveryAddressBlock);
            return fullText.contains(textToFind);
        } catch (Exception e) {
            return false;
        }
    }


    // Checks if the Billing Address block contains the specific text.
    public boolean verifyBillingAddressContains(String textToFind) {
        try {
            Edges_explicitWait(billingAddressBlock, 5);
            String fullText = Edges_getText(billingAddressBlock);
            return fullText.contains(textToFind);
        } catch (Exception e) {
            return false;
        }
    }

    // ************ ACTIONS ************

    public CheckoutPage enterComment(String comment) {
        Edges_scrollToElement(commentTextArea);
        Edges_sendKeys(commentTextArea, comment);
        return this;
    }

    public PaymentPage clickPlaceOrder() {
        Edges_scrollToElement(placeOrderButton);
        Edges_click(placeOrderButton);
        return new PaymentPage(driver); // You will need to create this class!
    }
    public DeletedAccountPage clickOnDeleteAccountButton(){ //click on the delete button
        Edges_click(deleteAccountButtonLocator);
        return new DeletedAccountPage(driver);
    }
}