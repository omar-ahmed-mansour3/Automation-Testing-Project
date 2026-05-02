package Tests;

import Pages.HomePage;
import Pages.TestCasesPage;
import Tests.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCasesTest extends BaseTest {

    @Test
    public void testCasesPageTest(){
        HomePage homePage = new HomePage(driver);
        TestCasesPage testCasesPage = homePage.clickOnTestCasesButton();
        Assert.assertTrue(testCasesPage.isInTestCasesPage(),
                "ERROR : the test case page title is not visible");
    }

}
