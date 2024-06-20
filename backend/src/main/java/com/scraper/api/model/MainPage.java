package com.scraper.api.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * MainPage class representing the main page of the Zehrs website.
 * This class contains web elements found on the page and their respective locators.
 * The elements are initialized using the PageFactory in Selenium.
 * Page URL: https://www.zehrs.ca/
 */
public class MainPage {
    /**
     * Web element for the 'Grocery' button identified by its data-code attribute
     */
    @FindBy(xpath = "//button[@data-code=\"xp-455-food-departments\"]")
    public WebElement gloceryMenuButton;

    /**
     * Web element for the 'Home, Beauty& Baby' button identified by its data-code attribute
     */
    @FindBy(xpath = "//button[@data-code=\"xp-455-nonfood-departments\"]")
    public WebElement homeBeautyBabyMenuButton;

    /**
     * Web element for the 'Joe Fresh' button identified by its data-code attribute
     */
    @FindBy(xpath = "//button[@data-code=\"xp-455-joe-fresh\"]")
    public WebElement joeFreshMenuButton;

    /**
     * Web element for the 'Discover Menu' button identified by its data-code attribute
     */
    @FindBy(xpath = "//button[@data-code=\"WhatsNew\"]")
    public WebElement discoverMenuButton;

    /**
     * Web element for the 'Drinks' submenu button identified by its href attribute
     */
    @FindBy(css = "a[href$=\"L2-Drinks\"]")
    public WebElement drinksSubMenuButton;

    /**
     * Web element for the 'Juice' submenu under 'Drinks' identified by its href attribute
     */
    @FindBy(css = "a[href$=\"L3-Drinks-Juice\"]")
    public WebElement drinksJuiceSubMenuButton;

    /**
     * Web element for the 'Coffee' submenu under 'Drinks' identified by its href attribute
     */
    @FindBy(css = "a[href$=\"L3-Drinks-Coffee\"]")
    public WebElement drinksCoffeeSubMenuButton;

    /**
     * Web element for the search field identified by its placeholder attribute
     */
    @FindBy(css = "input[placeholder=\"Search for product\"]")
    public WebElement searchField;

    /**
     * Web element for the clear search button identified by its title attribute
     */
    @FindBy(css = "button[title=\"Clear Search\"]")
    public WebElement searchClear;

    /**
     * Web element for the submit search button identified by its title attribute
     */
    @FindBy(css = "button[title=\"Submit Search\"]")
    public WebElement searchButton;

    /**
     * Web element for the rapid logo identified by its class name
     */
    @FindBy(className = "logo--rapid")
    public WebElement rapidLogo;

    /**
     * Web element for the page title identified by its class name
     */
    @FindBy(className = "page-title__title")
    public WebElement pageTitle;

    /**
     * List of web elements representing products
     */
    @FindBys(@FindBy(css = "div.chakra-linkbox"))
    public List<WebElement> products;

    /**
     * List of web elements representing search products
     */
    @FindBys(@FindBy(css = "div.product-tile"))
    public List<WebElement> searchProducts;

    /**
     * Web element for the footer 'Weekly Flyer' link identified by its data-track-link-name attribute
     */
    @FindBy(css = "a[data-track-link-name=\"popular-categories:weekly-flyer\"]")
    public WebElement footerWeeklyFlyer;

    /**
     * Web element for the footer 'Contact Us' link identified by its data-track-link-name attribute
     */
    @FindBy(css = "a[data-track-link-name=\"about-us:contact-us\"]")
    public WebElement footerContactUs;

    /**
     * Initializes the web elements using the given WebDriver instance.
     *
     * @param driver The WebDriver instance to be used for initializing the web elements.
     */
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
