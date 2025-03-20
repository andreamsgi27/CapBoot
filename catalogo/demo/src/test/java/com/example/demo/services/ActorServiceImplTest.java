package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.demo.entities.Actor;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.ActorRepository;
import com.example.demo.services.services.ActorServiceImpl;

public class ActorServiceImplTest {

    private ActorServiceImpl actorService;
    private ActorRepository actorRepository;
    
    //READS
    @Test
    public void testGetAllActor() {
        List<Actor> actors = List.of(new Actor(1, "John", "Doe"), new Actor(2, "Mark", "Bones"));
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        when(actorRepository.findAll()).thenReturn(actors);

        List<Actor> result = actorService.getAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Mark", result.get(1).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("Bones", result.get(1).getLastName());
    }

    @Test
    public void testGetActorById() {
        Actor actor = new Actor(1, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.findById(1)).thenReturn(java.util.Optional.of(actor));

        Actor result = actorService.getOne(1).get();
        assertEquals(1, result.getActorId());
    }

    //CREATES
    @Test
    public void testAddActor() throws DuplicateKeyException, InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.save(actor)).thenReturn(actor);
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        Actor result = actorService.add(actor);
        
        assertEquals(1, result.getActorId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testAddActorInvalidDataException() throws DuplicateKeyException, InvalidDataException {
        Actor actor = null;
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        assertThrows(InvalidDataException.class, () -> actorService.add(actor));
    }

    @Test
    public void testAddActorDuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> actorService.add(actor));
    }

    @Test
    public void testAddActorWithZeroId() throws DuplicateKeyException, InvalidDataException {
        Actor actor = new Actor(0, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.save(actor)).thenReturn(actor);

        Actor result = actorService.add(actor);
        
        assertEquals(0, result.getActorId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    //UPDATES
    @Test
    public void testUpdateActor() throws InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        Actor updatedActor = new Actor(1, "Mark", "Bones");

        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        
        when(actorRepository.save(updatedActor)).thenReturn(updatedActor);

        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        Actor result = actorService.modify(updatedActor);
        assertEquals("Mark", result.getFirstName());
        assertEquals("Bones", result.getLastName());
    }

    @Test
    public void testUpdateActorInvalidDataException() throws InvalidDataException {
        Actor actor = null;
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        assertThrows(InvalidDataException.class, () -> actorService.modify(actor));
    }

    //DELETES
    @Test
    public void testDeleteActor() throws InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        actorService.delete(actor);
        verify(actorRepository, times(1)).delete(actor);
    }

    @Test
    public void testDeleteByIdActor() throws InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);

        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        actorService.deleteById(1);
        verify(actorRepository, times(1)).delete(actor);
    }

    @Test
    public void testDeleteInvalid() throws InvalidDataException {
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        assertThrows(InvalidDataException.class, () -> actorService.delete(null));
    }

    @Test
    public void testDeleteByIdInvalid() throws InvalidDataException {
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
        assertThrows(InvalidDataException.class, () -> actorService.deleteById(null));
    }

}
