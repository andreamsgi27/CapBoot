package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Actor;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ActorRepository;
import com.example.demo.services.services.ActorServiceImpl;

class ActorServiceImplTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /* @Test
    void testAdd_ValidActor() throws DuplicateKeyException, InvalidDataException {
        Actor actor = new Actor();
        when(actorRepository.insert(actor)).thenReturn(actor);

        Actor result = actorService.add(actor);

        assertEquals(actor, result);
        verify(actorRepository, times(1)).insert(actor);
    } */

    @Test
    void testAdd_NullActor() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            actorService.add(null);
        });

        assertEquals("No puede ser nulo", exception.getMessage());
    }

    @Test
    void testAdd_InvalidActor() {
        Actor actor = mock(Actor.class);
        when(actor.isInvalid()).thenReturn(true);
        when(actor.getErrorsMessage()).thenReturn("Invalid data");
        when(actor.getErrorsFields()).thenReturn(Map.of("field1", "Invalid value 1", "field2", "Invalid value 2"));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            actorService.add(actor);
        });

        assertEquals("Invalid data", exception.getMessage());
    }

    /* @Test
    void testModify_ValidActor() throws NotFoundException, InvalidDataException {
        Actor actor = new Actor();
        when(actorRepository.update(actor)).thenReturn(actor);

        Actor result = actorService.modify(actor);

        assertEquals(actor, result);
        verify(actorRepository, times(1)).update(actor);
    } */

    @Test
    void testModify_NullActor() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            actorService.modify(null);
        });

        assertEquals("No puede ser nulo", exception.getMessage());
    }

    @Test
    void testDelete_ValidActor() throws InvalidDataException {
        Actor actor = new Actor();
        doNothing().when(actorRepository).delete(actor);

        actorService.delete(actor);

        verify(actorRepository, times(1)).delete(actor);
    }

    @Test
    void testDelete_NullActor() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            actorService.delete(null);
        });

        assertEquals("No puede ser nulo", exception.getMessage());
    }

    @Test
    void testDeleteById() {
        doNothing().when(actorRepository).deleteById(1);

        actorService.deleteById(1);

        verify(actorRepository, times(1)).deleteById(1);
    }

    @Test
    void testNovedades() {
        Timestamp timestamp = Timestamp.valueOf("2023-01-01 00:00:00");
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        List<Actor> actors = Arrays.asList(actor1, actor2);

        when(actorRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(timestamp)).thenReturn(actors);

        List<Actor> result = actorService.novedades(timestamp);

        assertEquals(2, result.size());
        verify(actorRepository, times(1)).findByLastUpdateGreaterThanEqualOrderByLastUpdate(timestamp);
    }
}