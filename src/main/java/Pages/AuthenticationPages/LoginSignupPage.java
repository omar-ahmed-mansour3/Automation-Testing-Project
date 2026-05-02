package Pages.AuthenticationPages;

import Pages.BasePage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSignupPage extends BasePage {

    public LoginSignupPage(WebDriver driver) {
        super(driver);
    }

    //********************LOCATORS*******************************//

    //general page locators
    private final By loginTitleLocator = By.cssSelector(".login-form>h2");
    private final By signupTitleLocator = By.cssSelector(".signup-form>h2");

    //login locators
    private final By loginEmailAddressLocator = By.cssSelector(".login-form [type='email']");
    private final By loginPasswordLocator = By.cssSelector(".login-form [type='password']");
    private final By loginButtonLocator = By.cssSelector(".login-form [type='submit']");
    //signup locators
    private final By signupNameLocator = By.cssSelector(".signup-form [type='text']");
    private final By signupEmailAddressLocator = By.cssSelector(".signup-form [type='email']");
    private final By signupButtonLocator = By.cssSelector(".signup-form [type='submit']");

    private final By signupAlreadyUsedMessageLocator =
            By.cssSelector("[action='/signup']>p");


    //*********************METHODS******************************************************************//

    //******************************Actions***********
    //*****************sign in methods
    public LoginSignupPage login_enter_email_address(String text){
        Edges_sendKeys(loginEmailAddressLocator, text);
        return this;
    }
    public LoginSignupPage login_enter_password(String text){
        Edges_sendKeys(loginPasswordLocator, text);
        return this;
    }
    public HomePage login_click_login(){
        Edges_click(loginButtonLocator);
        return new HomePage(driver);
    }

    //**************signup methods

    public LoginSignupPage signup_enter_name(String text){
        Edges_sendKeys(signupNameLocator, text);
        return this;
    }
    public LoginSignupPage signup_enter_email_address(String text){
        Edges_sendKeys(signupEmailAddressLocator, text);
        return this;
    }
    public SignUpPage signup_click_signup(){
        Edges_click(signupButtonLocator);
        return new SignUpPage(driver); // waiting for the following page stub
    }


    //******************************CHECKS***********

    public boolean isInLoginPage() {
        try {
            Edges_explicitWait(loginTitleLocator, 5); // check presence
            return Edges_isDisplayed(loginTitleLocator); // check visibility

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignupTitleVisible() {
        Edges_explicitWait(signupTitleLocator, 5); // check presence
        return Edges_isDisplayed(signupTitleLocator); // check visibility

    }
    public boolean isEmailAlreadyUsed() {
        Edges_explicitWait(signupAlreadyUsedMessageLocator, 5); // check presence
        return Edges_isDisplayed(signupAlreadyUsedMessageLocator); // check visibility

    }
}
