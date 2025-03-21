package com.example.demo.services.services;

import com.example.demo.core.ProjectionDomainService;
import com.example.demo.entities.Actor;

import java.util.Date;
import java.util.List;


public interface ActorService extends ProjectionDomainService<Actor, Integer> {
	void repartePremios();
	List<Actor> novedades(Date fecha);
}