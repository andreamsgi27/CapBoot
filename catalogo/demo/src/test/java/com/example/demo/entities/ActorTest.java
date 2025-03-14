package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActorTest {

    private Actor actor;

    @BeforeEach
    void setUp() {
        actor = new Actor(1, "John", "Doe");
    }

    @Test
    void testGettersAndSetters() {
        actor.setActorId(2);
        actor.setFirstName("Jane");
        actor.setLastName("Smith");
        actor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        assertEquals(2, actor.getActorId());
        assertEquals("Jane", actor.getFirstName());
        assertEquals("Smith", actor.getLastName());
        assertNotNull(actor.getLastUpdate());
    }

    @Test
    void testAddFilmActor() {
        FilmActor filmActor = new FilmActor();
        List<FilmActor> filmActors = new ArrayList<>();
        actor.setFilmActors(filmActors);

        actor.addFilmActor(filmActor);

        assertEquals(1, actor.getFilmActors().size());
        assertEquals(actor, filmActor.getActor());
    }

    @Test
    void testRemoveFilmActor() {
        FilmActor filmActor = new FilmActor();
        List<FilmActor> filmActors = new ArrayList<>();
        filmActors.add(filmActor);
        actor.setFilmActors(filmActors);

        actor.removeFilmActor(filmActor);

        assertTrue(actor.getFilmActors().isEmpty());
        assertNull(filmActor.getActor());
    }

    @Test
    void testEqualsAndHashCode() {
        Actor anotherActor = new Actor(1, "John", "Doe");
        assertEquals(actor, anotherActor);
        assertEquals(actor.hashCode(), anotherActor.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Actor [actorId=1, firstName=John, lastName=Doe, lastUpdate=null]";
        assertEquals(expected, actor.toString());
    }
}