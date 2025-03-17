package com.example.demo.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;

public interface DomainService<E, K> {
	List<E> getAll();
	
	Optional<E> getOne(K id);
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	
	E modify(E item) throws NotFoundException, InvalidDataException;
	
	void delete(E item) throws InvalidDataException;
<<<<<<< Updated upstream
	void deleteById(K id) throws InvalidDataException;
=======

	void deleteById(K id) throws InvalidDataException;
>>>>>>> Stashed changes
}