package com.example.demo.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.entities.Film;
import com.example.demo.repositories.repositories.ProjectionsAndSpecificationJpaRepository;

public interface FilmRepository extends ProjectionsAndSpecificationJpaRepository<Film, Integer> {
	List<Film> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}