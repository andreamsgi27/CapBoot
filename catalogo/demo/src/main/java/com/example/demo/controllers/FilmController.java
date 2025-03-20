package com.example.demo.controllers;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Film;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.services.FilmService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;

    // Constructor para la inyección de dependencias
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // Obtener todos los films
    @GetMapping
    public List<Film> getAll() {
        return filmService.getAll();
    }

    // Obtener todos los films con orden específico
    @GetMapping("/sorted")
    public List<Film> getAllSorted(@RequestParam Sort sort) {
        return filmService.getAll();
    }

    // Obtener un film por su ID
    @GetMapping("/{id}")
    public Optional<Film> getOne(@PathVariable Integer id) throws NotFoundException {
        return filmService.getOne(id);
    }

    // Crear un nuevo film
    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Film item) throws DuplicateKeyException, InvalidDataException {
        Film newFilm = filmService.add(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newFilm.getFilmId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Actualizar un film existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody Film item) throws BadRequestException, NotFoundException, InvalidDataException {
        if (!id.equals(item.getFilmId())) {
            throw new BadRequestException("El ID en la URL y el ID en el cuerpo de la solicitud no coinciden.");
        }
        filmService.modify(item);
    }

    // Eliminar un film por su ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws InvalidDataException {
        filmService.deleteById(id);
    }

    // Eliminar un film por objeto completo
    @DeleteMapping
    public void delete(@RequestBody Film item) throws InvalidDataException {
        filmService.delete(item);
    }

    // Obtener films con novedades basadas en la fecha de última actualización
    @GetMapping("/novedades")
    public List<Film> novedades(@RequestParam Timestamp fecha) {
        return filmService.novedades(fecha);
    }

    // Obtener films usando una especificación
    @GetMapping("/specification")
    public List<Film> getAllBySpecification(@RequestParam Specification<Film> spec) {
        return filmService.getAll(spec);
    }

    // Obtener films con paginación y especificación
    @GetMapping("/specification/paged")
    public Page<Film> getAllBySpecificationPaged(@RequestParam Specification<Film> spec, Pageable pageable) {
        return filmService.getAll(spec, pageable);
    }

    // Obtener films con paginación
    @GetMapping("/paged")
    public Page<Film> getAllPaged(Pageable pageable) {
        return filmService.getAll(pageable);
    }
}
