package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entities.Film;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.FilmRepository;
import com.example.demo.services.services.FilmServiceImpl;

public class FilmServiceImplTest {

    private FilmServiceImpl filmService;
    private FilmRepository filmRepository;

    //In
    private Film film1;
    private Film film2;

    @BeforeEach
    public void setup() {
        film1 = new Film(1, "Description 1", null, 120, "PG",
                        (short) 2001, (byte) 7, new BigDecimal("2.99"), new BigDecimal("19.99"),
                        "Film 1", null, null, null, null, null);
        film2 = new Film(
                            2, // filmId
                            "Description 2", // Description
                            null, // lastUpdate (not specified in the example)
                            150, // length (duration in minutes)
                            "PG-13", // rating
                            (short) 2005, // releaseYear (year of release)
                            (byte) 5, // rentalDuration (rental duration)
                            new BigDecimal("3.49"), // rentalRate (rental rate)
                            new BigDecimal("25.99"), // replacementCost (replacement cost)
                            "Film 2", // title
                            null, // language (relation with the language)
                            null, // languageVO (relation with the original language)
                            null, // filmActors (relation with film actors)
                            null, // filmCategories (relation with film categories)
                            null  // inventories (relation with inventories)
                );
    }

    // READS
    @Test
    public void testGetAllFilms() {
        List<Film> films = List.of(film1, film2);
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);
        when(filmRepository.findAll()).thenReturn(films);

        List<Film> result = filmService.getAll();

        assertEquals(2, result.size());
        assertEquals("Film 1", result.get(0).getTitle());
        assertEquals("Film 2", result.get(1).getTitle());
    }

    @Test
    public void testGetFilmById() {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));

        Film result = filmService.getOne(1).get();
        assertEquals(1, result.getFilmId());
        assertEquals("Film 1", result.getTitle());
    }

    // CREATES
    @Test
    public void testAddFilm() throws DuplicateKeyException, InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.save(film1)).thenReturn(film1);
        when(filmRepository.existsById(1)).thenReturn(false);

        Film result = filmService.add(film1);

        assertEquals(1, result.getFilmId());
        assertEquals("Film 1", result.getTitle());
    }

    @Test
    public void testAddFilmInvalidDataException() throws DuplicateKeyException, InvalidDataException {
        Film film = null;
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);
        assertThrows(InvalidDataException.class, () -> filmService.add(film));
    }

    @Test
    public void testAddFilmDuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> filmService.add(film1));
    }

    // UPDATES
    @Test
    public void testUpdateFilm() throws InvalidDataException, NotFoundException {
        film2.setFilmId(1);
        
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));
        when(filmRepository.save(film2)).thenReturn(film2);

        assertEquals(1, film1.getFilmId());
        assertEquals("Film 1", film1.getTitle());

        Film result = filmService.modify(film2);

        assertEquals("Film 2", result.getTitle());

        verify(filmRepository, times(1)).save(film2);
    }

    @Test
    public void testUpdateFilmInvalidDataException() throws InvalidDataException {
        Film film = null;
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);
        assertThrows(InvalidDataException.class, () -> filmService.modify(film));
    }

    // DELETES
    @Test
    public void testDeleteFilm() throws InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));
        filmService.delete(film1);
        verify(filmRepository, times(1)).delete(film1);
    }

    @Test
    public void testDeleteByIdFilm() throws InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));
        filmService.deleteById(1);
        verify(filmRepository, times(1)).delete(film1);
    }

    @Test
    public void testDeleteFilmInvalid() throws InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);
        assertThrows(InvalidDataException.class, () -> filmService.delete(null));
    }

    @Test
    public void testDeleteByIdFilmInvalid() throws InvalidDataException {
        filmRepository = mock(FilmRepository.class);
        filmService = new FilmServiceImpl(filmRepository);
        assertThrows(InvalidDataException.class, () -> filmService.deleteById(null));
    }
}