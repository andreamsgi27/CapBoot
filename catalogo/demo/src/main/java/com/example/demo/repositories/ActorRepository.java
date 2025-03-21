package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.Actor;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.repositories.ProjectionsAndSpecificationJpaRepository;

public interface ActorRepository extends ProjectionsAndSpecificationJpaRepository<Actor, Integer> {
	List<Actor> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Date fecha);
	
	default Actor insert(Actor item) throws DuplicateKeyException {
		if(existsById(item.getActorId()))
			throw new DuplicateKeyException();
		return save(item);
	}
	
	default Actor update(Actor item) throws NotFoundException {
		if(!existsById(item.getActorId()))
			throw new NotFoundException();
		return save(item);
	}
}
