package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SeleniumFrameWork {
    protected WebDriver driver;
    protected WebDriverWait explicitWait;
    protected final int DEFAULT_TIMEOUT = 10;

    // Initialize the browser
    // why use chrome when u can use the superior brave
    public void Edges_initializeBrowser() {
        // 1. Create ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // 2. TELL SELENIUM TO USE BRAVE INSTEAD OF CHROME
        // We use .setBinary() to point to the Brave executable
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

        // 3. Optional: Block the "Restore Pages" popup in Brave
        options.addArguments("--disable-application-cache");

        // 4. Initialize ChromeDriver with these options
        // Note: Selenium Manager will automatically find a matching ChromeDriver for you.
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        System.out.println("Edges: Brave Browser Initialized.");
    }

    // Browser implicitly wait
    public void Edges_implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("Edges: Set Implicit Wait to " + seconds + " seconds.");
    }

    // Explicit wait for element presence
    public void Edges_explicitWait(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println("Edges: Explicit wait for presence of " + locator);
    }

    // Fluent wait for element visibility with customizable timeout and polling interval
public void Edges_fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
    Wait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(timeoutSeconds))
            .pollingEvery(Duration.ofMillis(pollingMillis))
            .withMessage(timeoutMessage)
            .ignoring(NoSuchElementException.class);

    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    System.out.println("Edges: Fluent wait found element " + locator);
}


    // Navigate to URL
    public void Edges_navigateToURL(String url) {
        driver.get(url);
        System.out.println("Edges: Navigated to URL: " + url);
    }

    // Get page title
    public String Edges_getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Edges: Page title is '" + title + "'");
        return title;
    }

    // Get current URL
    public String Edges_getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        System.out.println("Edges: Current URL is '" + currentURL + "'");
        return currentURL;
    }

    // Click element using explicit wait
    public void Edges_click(By locator) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("Edges: Clicked element " + locator);
    }

    // Right click (context click) on element
    public void Edges_rightClick(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println("Edges: Right-clicked on element " + locator);
    }

    // Send keys to element
    public void Edges_sendKeys(By locator, String text) {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
        System.out.println("Edges: Sent keys to element " + locator);
    }

    // Get text from element
    public String Edges_getText(By locator) {
        String text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        System.out.println("Edges: Got text from element " + locator + ": " + text);
        return text;
    }

    // Dropdown handling by visible text
    public void Edges_selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Edges: Selected dropdown value by visible text: " + visibleText);
    }

    // Dropdown handling by value
    public void Edges_selectDropdownByValue(By locator, String value) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByValue(value);
        System.out.println("Edges: Selected dropdown value by value: " + value);
    }

    // Dropdown handling by index
    public void Edges_selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        System.out.println("Edges: Selected dropdown by index: " + index);
    }

    // Drag and drop element
    public void Edges_dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement target = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Edges: Dragged element " + sourceLocator + " and dropped on " + targetLocator);
    }

    // Checkbox handling: check checkbox
    public void Edges_checkCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Edges: Checked the checkbox " + locator);
        } else {
            System.out.println("Edges: Checkbox already checked " + locator);
        }
    }

    // Checkbox handling: uncheck checkbox
    public void Edges_uncheckCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Edges: Unchecked the checkbox " + locator);
        } else {
            System.out.println("Edges: Checkbox already unchecked " + locator);
        }
    }

    // Radio button handling: select radio button
    public void Edges_selectRadioButton(By locator) {
        WebElement radioButton = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!radioButton.isSelected()) {
            radioButton.click();
            System.out.println("Edges: Selected radio button " + locator);
        } else {
            System.out.println("Edges: Radio button already selected " + locator);
        }
    }

    // Window handle: switch to window by title
    public void Edges_switchToWindowByTitle(String windowTitle) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) {
                System.out.println("Edges: Switched to window with title: " + windowTitle);
                return;
            }
        }

        driver.switchTo().window(currentWindow);
        System.out.println("Edges: Window with title '" + windowTitle + "' not found. Stayed in original window.");
    }

    // Window handle: switch to window by handle
    public void Edges_switchToWindowByHandle(String windowHandle) {
        Set<String> allWindows = driver.getWindowHandles();
        if (allWindows.contains(windowHandle)) {
            driver.switchTo().window(windowHandle);
            System.out.println("Edges: Switched to window handle: " + windowHandle);
        } else {
            System.out.println("Edges: Window handle " + windowHandle + " does not exist. No switch performed.");
        }
    }


    // Close current window
    public void Edges_closeCurrentWindow() {
        driver.close();
        System.out.println("Edges: Closed current window.");
    }

    // Navigate back
    public void Edges_navigateBack() {
        driver.navigate().back();
        System.out.println("Edges: Navigated back.");
    }

    // Navigate forward
    public void Edges_navigateForward() {
        driver.navigate().forward();
        System.out.println("Edges: Navigated forward.");
    }

    // Refresh the page
    public void Edges_refreshPage() {
        driver.navigate().refresh();
        System.out.println("Edges: Page refreshed.");
    }

    // Scroll to element using JavaScript
//    public void Edges_scrollToElement(By locator) {
//        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        Actions actions = new Actions(driver);
//        actions.scrollToElement(element).perform();
//        System.out.println("Edges: Scrolled to element " + locator + " using Actions.scrollToElement().");
//    }

// modified the method to overcome ads that appear on button and cover some buttons
    public void Edges_scrollToElement(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
         // centers the element
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView({block: 'center'});",
                        element);
    }
    //OVERLOAD: Accept WebElement directly
    // This allows us to reuse the scroll logic inside loops where we don't have a unique locator
    public void Edges_scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Handle alert: accept alert
    public void Edges_acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("Edges: Alert accepted.");
    }


    // Handle alert: dismiss alert
    public void Edges_dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        System.out.println("Edges: Alert dismissed.");
    }

    // Handle alert: get alert text
    public String Edges_getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        System.out.println("Edges: Alert text: " + text);
        return text;
    }

    public void Edges_sendTextToAlert(String text) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    alert.sendKeys(text);
    alert.accept();
    System.out.println("Edges: Sent text to alert and accepted it: " + text);
    }

    // Close the browser
    public void Edges_closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Edges: Browser Closed.");
        }
    }

    //******************************ADDED METHODS******************************//
    // Check if element is visible
    public boolean Edges_isDisplayed(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // a javascript code to check if an element is visible on screen and not just on DOM
    public boolean Edges_isElementInViewport(By locator) {
        try {
            WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));


            return (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "return (" +
                            "   rect.top >= 0 &&" +
                            "   rect.left >= 0 &&" +
                            "   rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&" +
                            "   rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                            ");", element);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Edges_waitTillDisappear(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            // This returns TRUE if the element is gone (or invisible)
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
    }

    // this method returns a list of ALL instances of a certain locator
    public List<WebElement> Edges_getWebElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);

        System.out.println("Edges: Found " + elements.size() + " elements.");
        return elements;
    }

    // Hover over an element (Mouse Over)
    public void Edges_hover(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        System.out.println("Edges: Hovered over element " + locator);
    }

// ************************ GET ATTRIBUTE METHODS ************************

    //get attribute using string
    public String Edges_getAttribute(By locator, String attributeName) {
        try {
            WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            return null;
        }
    }

    // 2. Overload: If you already have the WebElement
    public String Edges_getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    // 3. NEW: Get a LIST of attributes
    public List<String> Edges_getListOfAttributes(By locator, String attributeName) {
        // Wait for at least one element to be present
        try {
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            // return the empty list from findElements below
        }

        //returns all elements that share that locator
        List<WebElement> elements = driver.findElements(locator);
        List<String> values = new ArrayList<>();

        for (WebElement element : elements) {
            //loop through each element, get attribute, and add it to the list/array of attributes
            values.add(element.getAttribute(attributeName));
        }
        return values;
    }

    //receives a list of web elements, and clicks the first available one of them
    // this method is used if there are sliding windows containing multiple elements, and you want ANY of them
    // there are alot of direct selenium methods here without using framework, since framework
    //generally deals with locators not with  web elements
    public String Edges_clickFirstVisibleAndReturnText(By clickLocator, By textLocator) {
        List<WebElement> clickables = driver.findElements(clickLocator);
        List<WebElement> texts = driver.findElements(textLocator);

        int loopSize = Math.min(clickables.size(), texts.size());

        for (int i = 0; i < loopSize; i++) {
            WebElement btn = clickables.get(i);
            if (btn.isDisplayed()) {
                String capturedText = texts.get(i).getText();
                System.out.println("Edges: Found visible element (" + i + "). Text: " + capturedText);

                Edges_scrollToElement(btn);

                btn.click();

                return capturedText;
            }
        }

        System.out.println("Edges: No visible element found to click for: " + clickLocator);
        return null;
    }
}
