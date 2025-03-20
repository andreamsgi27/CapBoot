package com.example.demo.services.services;

import com.example.demo.core.ProjectionDomainService;
import com.example.demo.entities.Actor;

import java.sql.Timestamp;
import java.util.List;


public interface ActorService extends ProjectionDomainService<Actor, Integer> {
	void repartePremios();
	List<Actor> novedades(Timestamp fecha);
}