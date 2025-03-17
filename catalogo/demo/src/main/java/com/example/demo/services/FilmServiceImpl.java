package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.entities.Film;
import com.example.demo.entities.Language;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

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
        if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }
        if (item.getFilmId() > 0 && filmRepository.existsById(item.getFilmId())) {
            throw new DuplicateKeyException("La película ya existe");
        }
        return filmRepository.save(item);
    }

    @Override
	public Film modify(Film item) throws InvalidDataException {
		if (item == null) {
			throw new InvalidDataException("La película no puede ser nula");
		}

		// Verifica si el Film con el ID proporcionado existe
		Film existingFilm = filmRepository.findById(item.getFilmId())
				.orElseThrow(() -> new InvalidDataException("La película no existe"));

		// Ahora, el objeto 'item' tiene las propiedades modificadas y debe guardarse tal cual
		return filmRepository.save(item);
	}

    @Override
    public void delete(Film item) throws InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }

        Film existingFilm = filmRepository.findById(item.getFilmId())
                .orElseThrow(() -> new InvalidDataException("La película no existe"));

        filmRepository.delete(existingFilm);
    }

    @Override
    public void deleteById(Integer id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("El ID no puede ser nulo");
        }

        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("La película no existe"));

        filmRepository.delete(existingFilm);
    }
}