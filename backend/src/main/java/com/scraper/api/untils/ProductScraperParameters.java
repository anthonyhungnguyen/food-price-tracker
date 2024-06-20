package com.scraper.api.untils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductScraperParameters {
    private String keywords;
    private Long totalProductsToFetch;

    // additional filters
    private String datePosted;
    private String sortBy;
}
