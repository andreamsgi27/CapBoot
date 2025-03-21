package com.example.demo.services.services;

import java.util.Date;
import java.util.List;

import com.example.demo.core.ProjectionDomainService;
import com.example.demo.core.SpecificationDomainService;
import com.example.demo.entities.Film;

public interface FilmService extends ProjectionDomainService<Film, Integer>, SpecificationDomainService<Film, Integer> {
	List<Film> novedades(Date fecha);
}