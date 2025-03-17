package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1);
        category.setName("Test Category");
    }

    @Test
    void testGettersAndSetters() {
        category.setCategoryId(2);
        category.setName("Updated Category");

        assertEquals(2, category.getCategoryId());
        assertEquals("Updated Category", category.getName());
    }
}