package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmActorPKTest {

    private FilmActorPK filmActorPK;

    @BeforeEach
    void setUp() {
        filmActorPK = new FilmActorPK();
        filmActorPK.setActorId(1);
        filmActorPK.setFilmId(2);
    }

    @Test
    void testGettersAndSetters() {
        filmActorPK.setActorId(3);
        filmActorPK.setFilmId(4);

        assertEquals(3, filmActorPK.getActorId());
        assertEquals(4, filmActorPK.getFilmId());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(filmActorPK.equals(filmActorPK));
    }

    @Test
    void testEquals_DifferentObjectSameValues() {
        FilmActorPK other = new FilmActorPK();
        other.setActorId(1);
        other.setFilmId(2);

        assertTrue(filmActorPK.equals(other));
    }

    @Test
    void testEquals_DifferentObjectDifferentValues() {
        FilmActorPK other = new FilmActorPK();
        other.setActorId(3);
        other.setFilmId(4);

        assertFalse(filmActorPK.equals(other));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(filmActorPK.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(filmActorPK.equals(new Object()));
    }

    @Test
    void testHashCode_SameValues() {
        FilmActorPK other = new FilmActorPK();
        other.setActorId(1);
        other.setFilmId(2);

        assertEquals(filmActorPK.hashCode(), other.hashCode());
    }

    @Test
    void testHashCode_DifferentValues() {
        FilmActorPK other = new FilmActorPK();
        other.setActorId(3);
        other.setFilmId(4);

        assertNotEquals(filmActorPK.hashCode(), other.hashCode());
    }
}
