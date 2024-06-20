package com.scraper.api.model;
import lombok.Data;

@Data
public class ProductData {
    private String name;
    private String brand;
    private String price;
    private String image;
    private String url;
    private String description;

    @Override
    public String toString() {
        return "Product {" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
