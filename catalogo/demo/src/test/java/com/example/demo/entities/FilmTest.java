package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FilmTest {

        @Test
        public void testFilmConstructor() {
            Language language = new Language(1, "English");
            BigDecimal rentalRate = new BigDecimal("2.99");
            BigDecimal replacementCost = new BigDecimal("19.99");
            Film film = new Film("Test Title", language, (byte) 5, rentalRate, replacementCost);

            assertEquals("Test Title", film.getTitle());
            assertEquals(language, film.getLanguage());
            assertEquals(5, film.getRentalDuration());
            assertEquals(rentalRate, film.getRentalRate());
            assertEquals(replacementCost, film.getReplacementCost());
        }

        @Test
        public void testSetAndGetSpecialFeatures() {
            Film film = new Film();
            film.addSpecialFeatures(Film.SpecialFeature.Trailers);
            film.addSpecialFeatures(Film.SpecialFeature.Commentaries);

            List<Film.SpecialFeature> features = film.getSpecialFeatures();
            assertTrue(features.contains(Film.SpecialFeature.Trailers));
            assertTrue(features.contains(Film.SpecialFeature.Commentaries));

            film.removeSpecialFeatures(Film.SpecialFeature.Trailers);
            features = film.getSpecialFeatures();
            assertFalse(features.contains(Film.SpecialFeature.Trailers));
        }

        @Test
        public void testSetAndGetActors() {
            Film film = new Film();
            Actor actor1 = new Actor(1, "John", "Doe");
            Actor actor2 = new Actor(2, "Jane", "Smith");

            film.addActor(actor1);
            film.addActor(actor2);

            List<Actor> actors = film.getActors();
            assertTrue(actors.contains(actor1));
            assertTrue(actors.contains(actor2));

            film.removeActor(actor1);
            actors = film.getActors();
            assertFalse(actors.contains(actor1));
        }

        @Test
        public void testSetAndGetCategories() {
            Film film = new Film();
            Category category1 = new Category(1, "Action");
            Category category2 = new Category(2, "Comedy");

            film.addCategory(category1);
            film.addCategory(category2);

            List<Category> categories = film.getCategories();
            assertTrue(categories.contains(category1));
            assertTrue(categories.contains(category2));

            film.removeCategory(category1);
            categories = film.getCategories();
            assertFalse(categories.contains(category1));
        }

        @Test
        public void testMerge() {
            Language language = new Language(1, "English");
            Film source = new Film("Source Title", language, (byte) 5, new BigDecimal("2.99"), new BigDecimal("19.99"));
            source.addSpecialFeatures(Film.SpecialFeature.Trailers);

            Film target = new Film("Target Title", language, (byte) 3, new BigDecimal("1.99"), new BigDecimal("9.99"));
            target = source.merge(target);

            assertEquals("Source Title", target.getTitle());
            assertEquals((byte) 5, target.getRentalDuration());
            assertEquals(new BigDecimal("2.99"), target.getRentalRate());
            assertTrue(target.getSpecialFeatures().contains(Film.SpecialFeature.Trailers));
        }

        @Test
        public void testSetAndGetRating() {
            Film film = new Film();
            film.setRating(Film.Rating.PARENTAL_GUIDANCE_SUGGESTED);

            assertEquals(Film.Rating.PARENTAL_GUIDANCE_SUGGESTED, film.getRating());
        }

        @Test
        public void testSetAndGetReleaseYear() {
            Film film = new Film();
            film.setReleaseYear((short) 2023);

            assertEquals(2023, (int) film.getReleaseYear());
        }
    }