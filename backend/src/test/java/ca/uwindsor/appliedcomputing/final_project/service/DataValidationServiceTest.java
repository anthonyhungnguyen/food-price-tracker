package ca.uwindsor.appliedcomputing.final_project.service;

import ca.uwindsor.appliedcomputing.final_project.dto.ValidationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidationServiceTest {

    private DataValidationService dataValidationService;

    @BeforeEach
    void setUp() {
        dataValidationService = new DataValidationService();
    }

    @Test
    void validate_ValidInput_ReturnsAllValid() {
        ValidationData data = new ValidationData();
        data.setProductName("Test Product");
        data.setStore("Store Name");
        data.setCategory("Food & Beverage");
        data.setPrice("$9.99");
        data.setImageUrl("https://example.com/image.jpg");
        data.setProductUrl("https://example.com/product");
        data.setProductDescription("A great product description.");

        ValidationData result = dataValidationService.validate(data);

        assertTrue(result.isValidName());
        assertTrue(result.isValidStore());
        assertTrue(result.isValidCategory());
        assertTrue(result.isValidPrice());
        assertTrue(result.isValidImageUrl());
        assertTrue(result.isValidProductUrl());
        assertTrue(result.isValidDescription());
    }

    @Test
    void validate_InvalidInput_ReturnsFalseFlags() {
        ValidationData data = new ValidationData();
        data.setProductName("Test Product @#$");
        data.setStore("Store Name 123");
        data.setCategory("Food & 123");
        data.setPrice("invalid");
        data.setImageUrl("not-a-url");
        data.setProductUrl("invalid-url");
        data.setProductDescription("Valid description");

        ValidationData result = dataValidationService.validate(data);

        assertFalse(result.isValidName());
        assertFalse(result.isValidStore());
        assertFalse(result.isValidCategory());
        assertFalse(result.isValidPrice());
        assertFalse(result.isValidImageUrl());
        assertFalse(result.isValidProductUrl());
        assertTrue(result.isValidDescription());
    }
}
