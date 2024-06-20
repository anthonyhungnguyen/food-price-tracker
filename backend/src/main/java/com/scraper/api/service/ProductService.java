package com.scraper.api.service;

import com.scraper.api.model.ProductData;

import java.util.Set;

public interface ProductService {
    Set<ProductData> getProducts();
    Set<ProductData> getProductsByKeyword(String keyword) throws Exception;
}
