package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LanguageTest {

    private Language language;

    @BeforeEach
    void setUp() {
        language = new Language();
        language.setLanguageId(1);
        language.setName("English");
    }

    @Test
    void testGettersAndSetters() {
        language.setLanguageId(2);
        language.setName("Spanish");

        assertEquals(2, language.getLanguageId());
        assertEquals("Spanish", language.getName());
    }

    /* @Test
    void testToString() {
        String expected = "Language [languageId=1, name=English]";
        assertEquals(expected, language.toString());
    } */
}