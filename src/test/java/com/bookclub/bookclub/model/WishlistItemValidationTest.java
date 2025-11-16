package com.bookclub.bookclub.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WishlistItemValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        validatorFactory.close();
    }

    @Test
    void invalidWishlistItem_hasViolations() {
        WishlistItem invalid = new WishlistItem("", "", null);
        Set<ConstraintViolation<WishlistItem>> violations = validator.validate(invalid);
        assertFalse(violations.isEmpty(), "Expected validation errors for empty isbn/title");
        assertTrue(violations.size() >= 2);
    }

    @Test
    void validWishlistItem_noViolations() {
        WishlistItem valid = new WishlistItem("9780316769532", "Catcher", "user");
        Set<ConstraintViolation<WishlistItem>> violations = validator.validate(valid);
        assertTrue(violations.isEmpty(), "No validation errors expected for valid item");
    }
}
