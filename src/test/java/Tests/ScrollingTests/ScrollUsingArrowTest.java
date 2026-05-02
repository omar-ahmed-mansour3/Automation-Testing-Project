package Tests.ScrollingTests;

import Pages.HomePage;
import Tests.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollUsingArrowTest extends BaseTest {

//    @Test
//    public void scrollWithArrowTest(){
//        HomePage homePage = new HomePage(driver);
//        Assert.assertTrue(homePage.isHomePageVisible(),
//                "ERROR: HOMEPAGE NOT VISIBLE");
//
////        Assert.assertFalse(homePage.isSubscriptionTextVisible(),
////                "subscription text ALWAYS visible");
//        //scroll down
//        homePage.scrollTillSubscribeIsOnScreen();
//        Assert.assertTrue(homePage.isSubscriptionTextVisible(),
//                "subscription text not visible");
//
////        Assert.assertFalse(homePage.isFullFledgedMessageVisible(),
////                "ERROR: FULL FLEDGED MESSAGE ALWAYS VISIBLE");
//        //go back up
//        homePage.clickOnGoToTopButton();
//        Assert.assertTrue(homePage.isFullFledgedMessageVisibleInViewport(),
//                "ERROR: FULL FLEDGED MESSAGE NOT VISIBLE");
//    }

    @Test
    public void ScrollUsingArrowTest(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "ERROR: HOMEPAGE NOT VISIBLE");

        // 1. Verify subscription is NOT visible yet (it's at the bottom)
        Assert.assertFalse(homePage.isSubscriptionTextVisibleInViewport(),
                "ERROR: Subscription text is visible without scrolling!");

        // 2. Scroll Down
        homePage.scrollTillSubscribeIsOnScreen();
        Assert.assertTrue(homePage.isSubscriptionTextVisibleInViewport(),
                "ERROR: subscription text not visible after scroll");

        // 3. Go back up
        homePage.clickOnGoToTopButton();

        // 4. Verify Top Element is back in Viewport
        Assert.assertTrue(homePage.isFullFledgedMessageVisibleInViewport(),
                "ERROR: FULL FLEDGED MESSAGE NOT VISIBLE");
    }
}
