package ca.uwindsor.appliedcomputing.final_project.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WebDriverHelperTest {

    @Mock
    private WebDriver driver;

    @Mock
    private WebElement element;

    private WebDriverHelper webDriverHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webDriverHelper = new WebDriverHelper(driver);
    }

    @Test
    void isElementPresent_ElementExists_ReturnsTrue() {
        By locator = By.id("test");
        when(driver.findElements(locator)).thenReturn(List.of(element));
        
        assertTrue(WebDriverHelper.isElementPresent(locator));
    }

    @Test
    void isElementPresent_ElementDoesNotExist_ReturnsFalse() {
        By locator = By.id("test");
        when(driver.findElements(locator)).thenReturn(List.of());
        
        assertFalse(WebDriverHelper.isElementPresent(locator));
    }

    @Test
    void getElementIfExist_ElementExists_ReturnsElement() {
        By locator = By.id("test");
        when(driver.findElements(locator)).thenReturn(List.of(element));
        when(driver.findElement(locator)).thenReturn(element);
        
        WebElement result = WebDriverHelper.getElementIfExist(locator);
        assertNotNull(result);
    }

    @Test
    void getElementIfExist_ElementDoesNotExist_ReturnsNull() {
        By locator = By.id("test");
        when(driver.findElements(locator)).thenReturn(List.of());
        
        WebElement result = WebDriverHelper.getElementIfExist(locator);
        assertNull(result);
    }
}
