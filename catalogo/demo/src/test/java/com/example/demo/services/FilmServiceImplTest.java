package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entities.Film;
import com.example.demo.entities.Language;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.FilmRepository;
import com.example.demo.services.services.FilmServiceImpl;

public class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;  // Usamos FilmServiceImpl directamente en la inyección

    @Mock
    private FilmRepository filmRepository;  // Mock de FilmRepository

    private Film film1;
    private Film film2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks

        Language language = new Language(1, "English");
        BigDecimal rentalRate = new BigDecimal("2.99");
        BigDecimal replacementCost = new BigDecimal("19.99");
        film1 = new Film(1, "Film 1", language, (byte) 5, rentalRate, replacementCost);

        Language language2 = new Language(2, "Spanish");
        BigDecimal rentalRate2 = new BigDecimal("3.99");
        BigDecimal replacementCost2 = new BigDecimal("29.99");
        film2 = new Film(2, "Film 2", language2, (byte) 5, rentalRate2, replacementCost2);
    }

    // READS
    @Test
    public void testGetAllFilms() {
        List<Film> films = List.of(film1, film2);

        when(filmRepository.findAll()).thenReturn(films);

        List<Film> result = filmService.getAll();

        assertEquals(2, result.size());
        assertEquals("Film 1", result.get(0).getTitle());
        assertEquals("Film 2", result.get(1).getTitle());
    }

    @Test
    public void testGetFilmById() {
        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));

        Film result = filmService.getOne(1).get();
        assertEquals(1, result.getFilmId());
        assertEquals("Film 1", result.getTitle());
    }

    // CREATES
    @Test
    public void testAddFilm() throws DuplicateKeyException, InvalidDataException {
        when(filmRepository.save(film1)).thenReturn(film1);
        when(filmRepository.existsById(1)).thenReturn(false);

        Film result = filmService.add(film1);

        assertEquals(1, result.getFilmId());
        assertEquals("Film 1", result.getTitle());
    }

    @Test
    public void testAddFilmInvalidDataException() throws DuplicateKeyException, InvalidDataException {
        Film film = null;
        assertThrows(InvalidDataException.class, () -> filmService.add(film));
    }

    @Test
    public void testAddFilmDuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
        when(filmRepository.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> filmService.add(film1));
    }

    // UPDATES
    @Test
    public void testUpdateFilm() throws InvalidDataException, NotFoundException {
        film2.setFilmId(1);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film1));
        when(filmRepository.save(film2)).thenReturn(film2);

        Film result = filmService.modify(film2);

        assertEquals("Film 2", result.getTitle());

        verify(filmRepository, times(1)).save(film2);
    }

    @Test
    public void testUpdateFilmInvalidDataException() throws InvalidDataException {
        Film film = null;
        assertThrows(InvalidDataException.class, () -> filmService.modify(film));
    }

    @Test
    public void testDeleteFilmInvalid() throws InvalidDataException {
        assertThrows(InvalidDataException.class, () -> filmService.delete(null));
    }

        // DELETES
        @Test
        void testDelete_NullFilm() {
            InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
                filmService.delete(null);
            });
    
            assertEquals("No puede ser nulo", exception.getMessage());
        }
    
        @Test
        void testDeleteById() {
            doNothing().when(filmRepository).deleteById(1);
    
            filmService.deleteById(1);
    
            verify(filmRepository, times(1)).deleteById(1);
        }
    
        //PAGEABLES Y SORT

        @Test
        public void testGetByProjectionWithType() {
            when(filmRepository.findAllBy(Film.class)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.getByProjection(Film.class);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }

        @Test
        public void testGetByProjectionWithSortAndType() {
            Sort sort = Sort.by("title");
            when(filmRepository.findAllBy(sort, Film.class)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.getByProjection(sort, Film.class);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }

        @Test
        public void testGetByProjectionWithPageableAndType() {
            Pageable pageable = mock(Pageable.class);
            Page<Film> page = mock(Page.class);
            when(filmRepository.findAllBy(pageable, Film.class)).thenReturn(page);

            Page<Film> result = filmService.getByProjection(pageable, Film.class);

            assertEquals(page, result);
        }

        @Test
        public void testGetAllWithSort() {
            Sort sort = Sort.by("title");
            when(filmRepository.findAll(sort)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.getAll(sort);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }

        @Test
        public void testGetAllWithPageable() {
            Pageable pageable = mock(Pageable.class);
            Page<Film> page = mock(Page.class);
            when(filmRepository.findAll(pageable)).thenReturn(page);

            Page<Film> result = filmService.getAll(pageable);

            assertEquals(page, result);
        }

        @Test
        public void testGetOneWithSpecification() {
            Specification<Film> spec = mock(Specification.class);
            when(filmRepository.findOne(spec)).thenReturn(Optional.of(film1));

            Optional<Film> result = filmService.getOne(spec);

            assertTrue(result.isPresent());
            assertEquals("Film 1", result.get().getTitle());
        }

        @Test
        public void testGetAllWithSpecification() {
            Specification<Film> spec = mock(Specification.class);
            when(filmRepository.findAll(spec)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.getAll(spec);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }

        @Test
        public void testGetAllWithSpecificationAndPageable() {
            Specification<Film> spec = mock(Specification.class);
            Pageable pageable = mock(Pageable.class);
            Page<Film> page = mock(Page.class);
            when(filmRepository.findAll(spec, pageable)).thenReturn(page);

            Page<Film> result = filmService.getAll(spec, pageable);

            assertEquals(page, result);
        }

        @Test
        public void testGetAllWithSpecificationAndSort() {
            Specification<Film> spec = mock(Specification.class);
            Sort sort = Sort.by("title");
            when(filmRepository.findAll(spec, sort)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.getAll(spec, sort);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }

        @Test
        public void testNovedades() {
            Date date = new Date(System.currentTimeMillis());
            when(filmRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date)).thenReturn(List.of(film1, film2));

            List<Film> result = filmService.novedades(date);

            assertEquals(2, result.size());
            assertEquals("Film 1", result.get(0).getTitle());
            assertEquals("Film 2", result.get(1).getTitle());
        }
    
}