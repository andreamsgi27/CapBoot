package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1);
        category.setName("Action");
        category.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        category.setFilmCategories(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        category.setCategoryId(2);
        category.setName("Comedy");
        category.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        assertEquals(2, category.getCategoryId());
        assertEquals("Comedy", category.getName());
        assertNotNull(category.getLastUpdate());
    }

    @Test
    void testAddFilmCategory() {
        FilmCategory filmCategory = new FilmCategory();
        category.addFilmCategory(filmCategory);

        assertEquals(1, category.getFilmCategories().size());
        assertEquals(category, filmCategory.getCategory());
    }

    @Test
    void testRemoveFilmCategory() {
        FilmCategory filmCategory = new FilmCategory();
        category.addFilmCategory(filmCategory);
        category.removeFilmCategory(filmCategory);

        assertTrue(category.getFilmCategories().isEmpty());
        assertNull(filmCategory.getCategory());
    }
}