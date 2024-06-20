package com.scraper.api.config;
import com.scraper.api.untils.WebDriverHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ScraperConfig {

    private WebDriver setupWebDriverInHost(boolean headlessMode) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            System.setProperty("webdriver.chrome.driver", path + "\\exe\\chromedriver-win64\\chromedriver.exe");
        else if (System.getProperty("os.name").toLowerCase().contains("linux"))
            System.setProperty("webdriver.chrome.driver", path + "/exe/chromedriver-linux64/chromedriver");
        else if (System.getProperty("os.name").toLowerCase().contains("mac"))
            System.setProperty("webdriver.chrome.driver", path + "/exe/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        //options.setPageLoadStrategy(PageLoadStrategy.EAGER); // implies that the WebDriver will wait for the entire page to load before moving on to the next step in the code.
        if(headlessMode) options.addArguments("--headless=new");

        // Selenium Grid Standalone
        // docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-chrome:latest
        //String wdUrl = "http://localhost:4444";
        //DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setBrowserName("chrome");
        //WebDriver webDriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
        //new ChromeDriver(options);
        //webDriver.manage().window().maximize();

        return new ChromeDriver(options);
    }

    private WebDriver setupWebDriverAuto(boolean headlessMode) {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        //options.setPageLoadStrategy(PageLoadStrategy.EAGER); // implies that the WebDriver will wait for the entire page to load before moving on to the next step in the code.
        if(headlessMode) options.addArguments("--headless=new");
        WebDriverManager.chromedriver().setup();

        return WebDriverManager.chromedriver().capabilities(options).create();
    }

    private WebDriver setupWebDriverDocker() {
        WebDriverManager wdm =  WebDriverManager.chromedriver().browserInDocker()
                .dockerDefaultArgs("--disable-gpu,--no-sandbox")
                .browserVersion("latest");
        return wdm.create();
    }

    public WebDriver setupWebDriver(boolean headlessMode)  {
        int opt = 2;
        WebDriver driver = null;
        switch (opt) {
            case 1:
                driver = setupWebDriverInHost(headlessMode);
                break;
            case 2:
                driver = setupWebDriverAuto(headlessMode);
                break;
            case 3:
                driver = setupWebDriverDocker();
                break;
            default:
                driver = setupWebDriverAuto(headlessMode);
                break;
        }
        return driver;
    }
}
