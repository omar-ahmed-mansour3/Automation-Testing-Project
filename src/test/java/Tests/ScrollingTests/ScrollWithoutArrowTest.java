package Tests.ScrollingTests;

import Pages.HomePage;
import Tests.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollWithoutArrowTest extends BaseTest {

    @Test
    public void scrollWithArrowTest(){
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
        homePage.scrollTOTop();

        // 4. Verify Top Element is back in Viewport
        Assert.assertTrue(homePage.isFullFledgedMessageVisibleInViewport(),
                "ERROR: FULL FLEDGED MESSAGE NOT VISIBLE");
    }
}