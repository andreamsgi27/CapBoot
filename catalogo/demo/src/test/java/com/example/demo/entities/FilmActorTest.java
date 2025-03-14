package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmActorTest {

    private FilmActor filmActor;

    @BeforeEach
    void setUp() {
        filmActor = new FilmActor();
        filmActor.setId(new FilmActorPK());
    }

    @Test
    void testGettersAndSetters() {
        FilmActorPK pk = new FilmActorPK();
        filmActor.setId(pk);

        assertEquals(pk, filmActor.getId());
    }

    /* @Test
    void testToString() {
        String expected = "FilmActor [id=" + filmActor.getId() + "]";
        assertEquals(expected, filmActor.toString());
    } */
}