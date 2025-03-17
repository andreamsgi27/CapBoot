package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmTest {

    private Film film;

    @BeforeEach
    void setUp() {
        film.setFilmId(1);
        film.setTitle("Test Film");
    }

    @Test
    void testGettersAndSetters() {
        film.setFilmId(2);
        film.setTitle("Updated Film");

        assertEquals(2, film.getFilmId());
        assertEquals("Updated Film", film.getTitle());
    }

    /* @Test
    void testToString() {
        String expected = "Film [filmId=1, title=Test Film]";
        assertEquals(expected, film.toString());
    } */
}