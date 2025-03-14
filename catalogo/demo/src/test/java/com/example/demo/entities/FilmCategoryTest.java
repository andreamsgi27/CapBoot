package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmCategoryTest {

    private FilmCategory filmCategory;

    @BeforeEach
    void setUp() {
        filmCategory = new FilmCategory();
        filmCategory.setId(new FilmCategoryPK());
    }

    @Test
    void testGettersAndSetters() {
        FilmCategoryPK pk = new FilmCategoryPK();
        filmCategory.setId(pk);

        assertEquals(pk, filmCategory.getId());
    }
}