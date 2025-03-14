package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmTextTest {

    private FilmText filmText;

    @BeforeEach
    void setUp() {
        filmText = new FilmText();
        filmText.setFilmId((short) 1);
        filmText.setTitle("Test Title");
    }

    @Test
    void testGettersAndSetters() {
        filmText.setFilmId((short) 2);
        filmText.setTitle("Updated Title");

        assertEquals(2, filmText.getFilmId());
        assertEquals("Updated Title", filmText.getTitle());
    }

    /* @Test
    void testToString() {
        String expected = "FilmText [filmId=1, title=Test Title]";
        assertEquals(expected, filmText.toString());
    } */
}