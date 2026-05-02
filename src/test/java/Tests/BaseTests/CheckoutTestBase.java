package Tests.BaseTests;

import Pages.CartRelatedPages.CheckoutPage;
import Pages.CartRelatedPages.PaymentDonePage;
import Pages.CartRelatedPages.PaymentPage;
import Utils.JsonDataTypes.BankData;
import Utils.JsonDataTypes.RegisterAndCheckoutData;
import Utils.JsonDataTypes.RegisterData;
import org.testng.Assert;

abstract public class CheckoutTestBase extends  CartTestBase{

    protected void verifyCheckoutAddress(CheckoutPage checkoutPage, RegisterAndCheckoutData data) {
        // Delivery Address
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressContains(data.user.firstName),
                "ERROR: Delivery Address missing First Name: " + data.user.firstName);
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressContains(data.user.lastName),
                "ERROR: Delivery Address missing Last Name: " + data.user.lastName);
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressContains(data.user.address),
                "ERROR: Delivery Address missing Address: " + data.user.address);
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressContains(data.user.city),
                "ERROR: Delivery Address missing City: " + data.user.city);
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressContains(data.user.mobileNumber),
                "ERROR: Delivery Address missing Mobile: " + data.user.mobileNumber);

        // Billing Address
        Assert.assertTrue(checkoutPage.verifyBillingAddressContains(data.user.firstName),
                "ERROR: Billing Address missing First Name");
        Assert.assertTrue(checkoutPage.verifyBillingAddressContains(data.user.address),
                "ERROR: Billing Address missing Address");
    }

    protected PaymentDonePage placeOrderAndPay(CheckoutPage checkoutPage, String comment, RegisterAndCheckoutData data) {
        checkoutPage.enterComment(comment);

        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        paymentPage.fillPaymentDetails(data.card);

        PaymentDonePage paymentDonePage = paymentPage.clickPayAndConfirmOrder();

        Assert.assertTrue(paymentDonePage.isOrderPlacedSuccessMessageVisible(),
                "ERROR: Order Placed Success Message NOT Visible!");

        return paymentDonePage;
    }

}
