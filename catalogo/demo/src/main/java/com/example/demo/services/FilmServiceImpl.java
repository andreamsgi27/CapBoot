package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Film;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.FilmRepository;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
        }

    @Override
    public Optional<Film> getOne(Integer id) {
        return filmRepository.findById(id);
	}

    @Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("No puede ser nulo");
		}
		if(item.getFilmId() > 0 && filmRepository.existsById(item.getFilmId())) {
			throw new DuplicateKeyException("Ya existe");
		}
		return filmRepository.save(item);
	}

    @Override
	public Film modify(Film item) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}
    
}
