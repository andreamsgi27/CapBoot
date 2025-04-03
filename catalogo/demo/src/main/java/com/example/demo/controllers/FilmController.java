package com.example.demo.controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.example.demo.DTOs.FilmDTO;
import com.example.demo.DTOs.FilmEditDTO;
import com.example.demo.DTOs.FilmShortDTO;
import com.example.demo.entities.Film;
import com.example.demo.entities.Language;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.FilmRepository;
import com.example.demo.services.services.FilmService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    private final FilmRepository filmRepository;

    // Constructor para la inyección de dependencias
    public FilmController(FilmService filmService, FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
        this.filmService = filmService;
    }

    // Obtener todos los films
    @GetMapping
    public List<FilmShortDTO> getAll() {
        return filmService.getAll().stream()
                .map(film -> new FilmShortDTO(film.getFilmId(), film.getTitle()))
                .toList();
    }

    // Obtener todos los films con orden específico.
    @GetMapping("/sorted")
    public List<FilmShortDTO> getAllSorted(@RequestParam Sort sort) {
        return StreamSupport.stream(filmService.getAll(sort).spliterator(), false)
                .map(film -> new FilmShortDTO(film.getFilmId(), film.getTitle()))
                .toList();
    }

    // Obtener un film por su ID
    @GetMapping("/{id}")
    public Optional<Film> getOne(@PathVariable Integer id) throws NotFoundException {
        return filmService.getOne(id);
    }

    // Crear un nuevo film
    @PostMapping
    public ResponseEntity<FilmDTO> add(@Valid @RequestBody FilmDTO item) 
            throws DuplicateKeyException, InvalidDataException {
        
        // Convertir FilmsDTO a Film
        Film film = new Film(
                item.getFilmId(),
                item.getTitle(), // Asegúrate de pasar los datos correctos, si es necesario
                item.getLanguage(),  // Convertir el DTO Language a Entity Language
                item.getRentalRate(),
                item.getReplacementCost(),
                item.getRentalDuration()  // Asume que Rating es parte de FilmDetailsDTO, si no deberías agregarlo
        );

        // Guardar la entidad Film utilizando el servicio
        film = filmService.add(film);
        
        // Crear la URI para la nueva entidad Film
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(film.getFilmId())
                .toUri();
        
        // Devolver la respuesta con el DTO FilmDetailsDTO
        return ResponseEntity.created(location)
                .body(new FilmDTO(film.getFilmId(), film.getTitle(), film.getLanguage(), film.getRentalRate(), film.getReplacementCost(), film.getRentalDuration()));
    }

    // Actualizar un film existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody FilmEditDTO item) 
            throws BadRequestException, NotFoundException, InvalidDataException {
        
        if (!id.equals(item.getFilmId())) {
            throw new BadRequestException("El ID en la URL y el ID en el cuerpo de la solicitud no coinciden.");
        }

        // Obtener la entidad Film actual desde la base de datos
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Film no encontrado."));

        // Actualizar solo los campos proporcionados en el cuerpo de la solicitud
        if (item.getTitle() != null) {
            existingFilm.setTitle(item.getTitle());
        }
        if (item.getDescription() != null) {
            existingFilm.setDescription(item.getDescription());
        }
        if (item.getLength() != null) {
            existingFilm.setLength(item.getLength());
        }
        if (item.getRating() != null) {
            existingFilm.setRating(Film.Rating.getEnum(item.getRating()));
        }
        if (item.getReleaseYear() != null) {
            existingFilm.setReleaseYear(item.getReleaseYear());
        }
        if (item.getLanguageVOId() != null) {
            existingFilm.setLanguageVO(new Language(item.getLanguageVOId(), null));
        }
        if (item.getLanguage() != null) {
            existingFilm.setLanguage(item.getLanguage());
        }
        if (item.getRentalRate() != null) {
            existingFilm.setRentalRate(item.getRentalRate());
        }
        if (item.getReplacementCost() != null) {
            existingFilm.setReplacementCost(item.getReplacementCost());
        }
        if (item.getRentalDuration() != null) {
            existingFilm.setRentalDuration(item.getRentalDuration());
        }

        // Modificar la entidad Film utilizando el servicio
        filmService.modify(existingFilm);
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
    public List<Film> novedades(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fecha) {
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
