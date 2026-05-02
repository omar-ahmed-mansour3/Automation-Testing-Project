package Pages;

import Utils.SeleniumFrameWork;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage extends SeleniumFrameWork {
    public BasePage(WebDriver driver) {
        this.driver = driver;

        this.explicitWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        // a fix for a complicated null ptr exception
        //it Manually initializes the explicitWait for this Page

    }
}
