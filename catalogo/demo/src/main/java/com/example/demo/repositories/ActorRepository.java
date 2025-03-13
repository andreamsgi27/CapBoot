package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    
}
