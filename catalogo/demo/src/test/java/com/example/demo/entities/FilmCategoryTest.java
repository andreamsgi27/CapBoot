package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmCategoryPKTest {

    private FilmCategoryPK filmCategoryPK;

    @BeforeEach
    void setUp() {
        filmCategoryPK = new FilmCategoryPK();
        filmCategoryPK.setFilmId(1);
        filmCategoryPK.setCategoryId((byte) 2);
    }

    @Test
    void testGettersAndSetters() {
        filmCategoryPK.setFilmId(3);
        filmCategoryPK.setCategoryId((byte) 4);

        assertEquals(3, filmCategoryPK.getFilmId());
        assertEquals((byte) 4, filmCategoryPK.getCategoryId());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(filmCategoryPK.equals(filmCategoryPK));
    }

    @Test
    void testEquals_DifferentObjectSameValues() {
        FilmCategoryPK other = new FilmCategoryPK();
        other.setFilmId(1);
        other.setCategoryId((byte) 2);

        assertTrue(filmCategoryPK.equals(other));
    }

    @Test
    void testEquals_DifferentObjectDifferentValues() {
        FilmCategoryPK other = new FilmCategoryPK();
        other.setFilmId(3);
        other.setCategoryId((byte) 4);

        assertFalse(filmCategoryPK.equals(other));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(filmCategoryPK.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(filmCategoryPK.equals(new Object()));
    }

    @Test
    void testHashCode_SameValues() {
        FilmCategoryPK other = new FilmCategoryPK();
        other.setFilmId(1);
        other.setCategoryId((byte) 2);

        assertEquals(filmCategoryPK.hashCode(), other.hashCode());
    }

    @Test
    void testHashCode_DifferentValues() {
        FilmCategoryPK other = new FilmCategoryPK();
        other.setFilmId(3);
        other.setCategoryId((byte) 4);

        assertNotEquals(filmCategoryPK.hashCode(), other.hashCode());
    }
}