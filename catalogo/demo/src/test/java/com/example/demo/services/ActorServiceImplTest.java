package com.example.demo.services;

import org.junit.jupiter.api.Test;

import com.example.demo.entities.Actor;
import com.example.demo.repositories.ActorRepository;

public class ActorServiceImplTest {

    private ActorServiceImpl actorService;
    private ActorRepository actorRepository;
    
    @Test
    public void testUpdateActor() {
        // Arrange
        Actor actor = new Actor(1, "John", "Doe");

    }
}
