package Pages.CartRelatedPages;

import Pages.BasePage;
import Utils.JsonDataTypes.BankData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    // ************************* LOCATORS *************************


    private final By nameOnCardInputLocator = By.name("name_on_card");
    private final By cardNumberInputLocator = By.name("card_number");
    private final By cvcInputLocator = By.name("cvc");
    private final By expiryMonthInputLocator = By.name("expiry_month");
    private final By expiryYearInputLocator = By.name("expiry_year");

    private final By payButtonLocator = By.id("submit");
    private final By successMessageLocator = By.cssSelector(".alert-success");



    // ************************* METHODS *************************

    //**************ACTIONS
    public PaymentPage fillPaymentDetails(BankData bankData) {
        Edges_explicitWait(nameOnCardInputLocator, 5);

        Edges_sendKeys(nameOnCardInputLocator, bankData.nameOnCard);
        Edges_sendKeys(cardNumberInputLocator, bankData.cardNumber);
        Edges_sendKeys(cvcInputLocator, bankData.cvc);
        Edges_sendKeys(expiryMonthInputLocator, bankData.expiryMonth);
        Edges_sendKeys(expiryYearInputLocator, bankData.expiryYear);

        return this;
    }

    public PaymentDonePage clickPayAndConfirmOrder() {
        Edges_scrollToElement(payButtonLocator);
        Edges_click(payButtonLocator);
        return new PaymentDonePage(driver);
    }

    //*************CHECKS
    public boolean iSuccessMessageVisible(){
        try {
            return Edges_isDisplayed(successMessageLocator);
        } catch (Exception e) {
            return false;
        }
    }

}