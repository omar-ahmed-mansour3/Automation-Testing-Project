package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage{
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }
    //***********************************LOCATORS**************************************
    private final By testCasesTitleLocator = By.cssSelector(".title.text-center>b");


    //***************************METHODS********************************************
    public boolean isInTestCasesPage(){
        Edges_explicitWait(testCasesTitleLocator , 5);
        return Edges_isDisplayed(testCasesTitleLocator);
    }
}
