package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.stereotype.Service;
import com.example.demo.CatalogoApplication;
import com.example.demo.entities.Actor;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {

    private final CatalogoApplication catalogoApplication;
	private ActorRepository actorRepository;
	
	public ActorServiceImpl(ActorRepository actorRepository, CatalogoApplication catalogoApplication) {
		this.actorRepository = actorRepository;
		this.catalogoApplication = catalogoApplication;
	}

	@Override
	public List<Actor> getAll() {
		return actorRepository.findAll();
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		return actorRepository.findById(id);
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("El actor no puede ser nulo");
		}
		if(item.getActorId() > 0 && actorRepository.existsById(item.getActorId())) {
			throw new DuplicateKeyException("El actor ya existe");
		}
		return actorRepository.save(item);
	}

	@Override
	public Actor modify(Actor item) throws InvalidDataException {
		Actor existingActor = actorRepository.findById(item.getActorId()).orElse(null);
		if(existingActor == null) {
			throw new InvalidDataException("El actor no existe");
		} else {
			existingActor.setFirstName(item.getFirstName());
			existingActor.setLastName(item.getLastName());
			return actorRepository.save(existingActor);
		}
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	/* @Override
	public void repartePremios()  {
		// TODO Auto-generated method stub

	} */

}