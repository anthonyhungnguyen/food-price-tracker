package com.scraper.api.untils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebDriverHelper {

    private static WebDriver driver;

    // Actions instance for performing complex user interactions
    private static Actions actions;

    // JavascriptExecutor instance for executing JavaScript commands
    private static JavascriptExecutor js;

    public WebDriverHelper(WebDriver webDriver) {
        driver = webDriver;
        js = (JavascriptExecutor) driver;
    }

    public static void init(WebDriver webDriver) {
        driver = webDriver;
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }
    public static boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public static WebElement getElementIfExist(By locator) {
        if (isElementPresent(locator)) return driver.findElement(locator);
        return null;
    }

    public static List<WebElement> getElementsIfExists(By locator) {
        if (isElementPresent(locator)) return driver.findElements(locator);
        return null;
    }

    public static WebElement getRelatedElementIfExist(WebElement element, By relatedLocator) {
        try {
            return element.findElement(relatedLocator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
        waitInSeconds(5);
    }

    public static void waitUntilElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilExpectedPageLoaded(String expectedUrl, By elementLocator) throws Exception {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.urlContains(expectedUrl));
            if (elementLocator != null) {
                wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            }
        } catch (Exception e) {
            throw new Exception(String.format("Expected page that should contain '%s' not loaded in 30 seconds", expectedUrl));
        }
    }

    public static void goToPage(String url) {
        if (url.isBlank()) throw new RuntimeException("Can't go to the page, provided url is empty or null");
        driver.get(url);
    }

    public static String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Method to wait for a specified number of seconds.
     * Uses implicit wait to pause the test execution.
     *
     * @param seconds Number of seconds to wait.
     */
    public static void waitInSeconds(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Method to click a web element using JavaScript.
     * Waits for the element to be displayed before clicking it.
     *
     * @param element The web element to be clicked.
     */
    public static void clickByJs(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> element.isDisplayed());
        if (js != null) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static void shutDownScraper() {
        driver.quit();
    }
}