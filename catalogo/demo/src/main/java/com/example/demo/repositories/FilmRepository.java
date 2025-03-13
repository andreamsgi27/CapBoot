package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film>{
    
}
