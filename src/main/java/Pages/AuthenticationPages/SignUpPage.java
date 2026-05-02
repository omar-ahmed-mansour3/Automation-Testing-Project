package Pages.AuthenticationPages;

import Pages.BasePage;
import Utils.JsonDataTypes.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    //**********************LOCATORS******************************************

    private final By signupMaleTitleCheckbox =
            By.cssSelector(".clearfix #uniform-id_gender1");
    private final By signupFemaleTitleCheckbox =
            By.cssSelector(".clearfix #uniform-id_gender2");
    private final By accountInfoTitle  =
            By.cssSelector(".login-form>.title.text-center>b");//get the text inside it
    private final By nameTextFieldLocator = By.id("name");
    private final By passwordTextFieldLocator = By.id("password");

    private final By dayDropDownMenuLocator = By.cssSelector("#days.form-control");
    private final By monthDropDownMenuLocator = By.cssSelector("#months.form-control");
    private final By yearDropDownMenuLocator = By.cssSelector("#years.form-control");

    private final By newsLetterCheckboxLocator = By.id("newsletter");
    private final By offersCheckboxLocator = By.id("optin");
    private final By firstNameTextBox = By.id("first_name");
    private final By lastNameTextBox = By.id("last_name");
    private final By companyNameTextBox = By.id("company");
    private final By addressTextBox = By.id("address1");

    private final By countryDropDownMenuLocator = By.cssSelector("#country.form-control");

    private final By stateTextBox = By.id("state");
    private final By cityTextBox = By.id("city");
    private final By zipcodeTextBox = By.id("zipcode");
    private final By mobileNumberTextBox = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa='create-account']");



//********************** METHODS ******************************************

    //************************Actions

    // Fills all the form data in strict page order.
    public SignUpPage fillAccountInformation(RegisterData data) {

        // 1. Title (Gender) - Top of the form
        Edges_click(signupMaleTitleCheckbox);

        // 2. Password
        Edges_sendKeys(passwordTextFieldLocator, data.password);

        // 3. Date of Birth (Day -> Month -> Year)
        Edges_selectDropdownByVisibleText(dayDropDownMenuLocator, data.day);
        Edges_selectDropdownByVisibleText(monthDropDownMenuLocator, data.month);
        Edges_selectDropdownByVisibleText(yearDropDownMenuLocator, data.year);

        // 4. Checkboxes (Newsletter -> Special Offers)
        Edges_click(newsLetterCheckboxLocator);
        Edges_click(offersCheckboxLocator);

        // 5. Personal Details (First Name -> Last Name -> Company -> Address)
        Edges_sendKeys(firstNameTextBox, data.firstName);
        Edges_sendKeys(lastNameTextBox, data.lastName);
        Edges_sendKeys(companyNameTextBox, data.company);
        Edges_sendKeys(addressTextBox, data.address);

        // 6. Country (Middle of address block)
        Edges_selectDropdownByVisibleText(countryDropDownMenuLocator, data.country);

        // 7. Remaining Details (State -> City -> Zip -> Mobile)
        Edges_sendKeys(stateTextBox, data.state);
        Edges_sendKeys(cityTextBox, data.city);
        Edges_sendKeys(zipcodeTextBox, data.zipcode);
        Edges_sendKeys(mobileNumberTextBox, data.mobileNumber);

        return this;
    }


    public AccountCreatedPage clickCreateAccount() {
        Edges_click(createAccountButton);
        return new AccountCreatedPage(driver);
    }

    //***************checks
    public boolean isAccountInfoPageVisible() {
        return Edges_isDisplayed(accountInfoTitle);
    }


}
