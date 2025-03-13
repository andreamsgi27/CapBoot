package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    List<Actor> findByFirstNameStartingWithOrderByLastNameDesc(String prefix);
    /* List<Actor> findByActorIdGreaterThan(int id);
    @Query(value = "SELECT a FROM Actor a WHERE a.actor_id > ?1 id")
    List<Actor> findNewActorsJPQL(int id);
    @Query(value = "SELECT a FROM actor a WHERE actor_id > :id", nativeQuery = true)
    List<Actor> findNewActorsSQL(int id); */
}
