package com.scraper.api.controller;

import com.scraper.api.model.KeywordSearchData;
import com.scraper.api.model.ProductData;
import com.scraper.api.service.KeywordService;
import com.scraper.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class ScraperController {
    @Autowired
    private ProductService productService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping
    @ResponseBody
    public String index() {
        // TODO
        return "<!DOCTYPE html><html lang=\"en\">"
                + "<head><title>Selenium / Spring Boot demo</title></head>"
                + "<body><h1 id=\"h1-hello\">Hello world!</h1></body></html>";
    }

    @GetMapping(path = "/word-complete")
    public Set<String> getWordCompleteList(@RequestParam("q") String text) throws Exception {
        // TODO
        return Set.of();
    }

    @GetMapping(path = "/word-count")
    public int getWordCount(@RequestParam("q") String url) throws Exception {
        // TODO
        return 0;
    }

    @GetMapping(path = "/keyword-search")
    public Set<KeywordSearchData> getKeywordSearchFrequency(@RequestParam("q") String searchKeyword) throws Exception {
        return keywordService.setKeywordSearched(searchKeyword);
    }

    @GetMapping(path = "/keyword-search-list")
    public Set<KeywordSearchData> getKeywordSearchedList(@RequestParam("q") String type) throws Exception {
        if (type != null && type.equalsIgnoreCase("top")) {
            return keywordService.getTopKeywordsSearched();
        } else if (type != null && type.equalsIgnoreCase("recent")) {}
        return keywordService.getRecentKeywordsSearched();
    }

    @GetMapping(path = "/products/scraping")
    public Set<ProductData> getProductsByKeyword(@RequestParam("q") String searchKeyword) throws Exception {
        return productService.getProductsByKeyword(searchKeyword);
    }

    @GetMapping(path = "/products/")
    public Set<ProductData> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/page-ranking")
    public int getPageRanking() {
        // TODO
        return 0;
    }
}
