package com.scraper.api.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.scraper.api.config.ScraperConfig;
import com.scraper.api.model.MainPage;
import com.scraper.api.model.ProductData;
import com.scraper.api.untils.WebDriverHelper;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final ScraperConfig scraperConfig = new ScraperConfig();
    private static WebDriver driver;
    private static WebDriverHelper webDriverHelper;

    //Reading data from property file to a list
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    private void webDriverInit() {
        if (driver == null) {
            driver = scraperConfig.setupWebDriver(true);
            WebDriverHelper.init(driver);
        }
    }

    private  void webDriverRelease() {
        WebDriverHelper.shutDownScraper();
        driver = null;
    }

    @Override
    public Set<ProductData> getProducts() {
        // init web driver
        webDriverInit();
        String filePath = "./data/products_zehrs.csv";

        Set<ProductData> responseProducts = new HashSet<>();
            try (CSVReader csvReader = new CSVReader(new FileReader(Paths.get(filePath).toFile()))) {
                List<String[]> records = csvReader.readAll();
                records.remove(0); // Remove header row

                for (String[] record : records) {
                    ProductData responseProduct = new ProductData();
                    if (record.length == 7) {
                        responseProduct.setName(record[1]);
                        responseProduct.setBrand(record[2]);
                        responseProduct.setPrice(record[3]);
                        responseProduct.setImage(record[4]);
                        responseProduct.setUrl(record[5]);
                        responseProduct.setDescription(record[6]);
                        if (responseProduct.getName() != null)
                            responseProducts.add(responseProduct);
                    }
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }

        return responseProducts;
    }

    @Override
    public Set<ProductData> getProductsByKeyword(String keyword) throws Exception {
        // init web driver
        webDriverInit();

        Set<ProductData> responseProducts = new HashSet<>();
        //Traversing through the urls
        for (String url: urls) {
            String fullUrl = url + keyword;
            driver.get(fullUrl);
            if (url.contains("zehrs")) {
                WebDriverHelper.waitUntilExpectedPageLoaded(fullUrl, By.className("responsive-image--product-tile-image"));
                extractDataFromZehrs(responseProducts);
            }
        }
        // release web driver
        webDriverRelease();

        return responseProducts;
    }

    private void extractDataFromZehrs(Set<ProductData> responseProducts) {
        try {
            // Find product elements
            MainPage mainPage = new MainPage(driver);

            String csvFile = "data/products_zehrs.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
            String[] header = {"No", "Product Name", "Brand", "Price", "Image URL", "Product URL", "Product Details"};
            writer.writeNext(header);

            int count = 1;
            int limitCheck = 1;
            List<WebElement> searchProducts = mainPage.searchProducts;
            WebDriverHelper.waitInSeconds(10);
            for (WebElement product: searchProducts) {

                ProductData responseProduct = new ProductData();
                try {
                    WebElement we = product.findElement(By.cssSelector("span.product-name__item--name"));
                    if (we != null && !StringUtils.isEmpty(we.getText()) ) {
                        // trick to load full element
                        WebDriverHelper.moveToElement(we);
                        responseProduct.setName(we.getText());
                    }

                    we = product.findElement(By.cssSelector("span.product-name__item--brand"));
                    if (we != null && !StringUtils.isEmpty(we.getText()) ) {
                        responseProduct.setBrand(we.getText());
                    }

                    try {
                        we = product.findElement(By.cssSelector("span.selling-price-list__item__price--now-price__value"));
                    } catch (NoSuchElementException e) {
                        try {
                            we = product.findElement(By.cssSelector("span.selling-price-list__item__price--sale__value"));
                        }  catch (NoSuchElementException e2) {
                            // do nothing
                        }
                    }
                    assert we != null;
                    responseProduct.setPrice(we.getText());

                    we = WebDriverHelper.getRelatedElementIfExist(product, By.className("responsive-image--product-tile-image"));
                    assert we != null;
                    WebDriverHelper.waitUntilElementPresent(we);
                    if (!StringUtils.isEmpty(we.getAttribute("src"))) {
                        responseProduct.setImage(we.getAttribute("src"));
                    }

                    we = WebDriverHelper.getRelatedElementIfExist(product, By.className("product-tile__details__info__name__link"));
                    assert we != null;
                    WebDriverHelper.waitUntilElementPresent(we);
                    String productLink = we.getAttribute("href");
                    if (!StringUtils.isEmpty(productLink)) {
                        responseProduct.setUrl(productLink);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (responseProduct.getName() != null) {
                    responseProducts.add(responseProduct);
                    limitCheck++;
                    if (limitCheck > 30) {
                        break;
                    }
                }
            }

            for (ProductData product: responseProducts) {
                if (product.getUrl() != null) {
                    // get product details
                    driver.get(product.getUrl());
                    WebDriverHelper.waitUntilExpectedPageLoaded(product.getUrl(), By.className("product-description-text__text"));
                    WebElement descriptionDiv = driver.findElement(By.cssSelector("div.product-description-text__text"));
                    assert descriptionDiv != null;
                    product.setDescription(descriptionDiv.getText());
                    WebDriverHelper.waitInSeconds(10);

                    // && responseProduct.getBrand() != null && responseProduct.getPrice() != null && responseProduct.getImage() != null && responseProduct.getUrl() != null) {
                    String[] data = { String.valueOf(count++), product.getName(), product.getBrand(), product.getPrice(), product.getImage(), product.getUrl(), product.getDescription() };
                    writer.writeNext(data);
                }
            }

            // Close CSV writer and browser
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
