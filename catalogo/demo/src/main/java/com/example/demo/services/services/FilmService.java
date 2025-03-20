package com.example.demo.services.services;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.core.ProjectionDomainService;
import com.example.demo.core.SpecificationDomainService;
import com.example.demo.entities.Film;

public interface FilmService extends ProjectionDomainService<Film, Integer>, SpecificationDomainService<Film, Integer> {
	List<Film> novedades(Timestamp fecha);
}