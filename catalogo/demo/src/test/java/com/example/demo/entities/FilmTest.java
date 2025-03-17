package com.example.demo.entities;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FilmTest {

    @Test
    public void testFilmConstructorAndGettersSetters() {
        // Crear un objeto Film
        Film film = new Film(1, "Description", null, 120, "PG", (short) 2001, (byte) 7,
                            new BigDecimal("2.99"), new BigDecimal("19.99"), "Film 1", null, null, null, null, null);

        // Verificar los valores de los atributos a través de los métodos getters
        assertEquals(1, film.getFilmId());
        assertEquals("Description", film.getDescription());
        assertEquals(120, film.getLength());
        assertEquals("PG", film.getRating());
        assertEquals((short) 2001, film.getReleaseYear());
        assertEquals((byte) 7, film.getRentalDuration());
        assertEquals(new BigDecimal("2.99"), film.getRentalRate());
        assertEquals(new BigDecimal("19.99"), film.getReplacementCost());
        assertEquals("Film 1", film.getTitle());
    }

    @Test
    public void testFilmToString() {
        // Crear un objeto Film con todos los campos, incluyendo lastUpdate
        Film film = new Film(1, "Description", new Timestamp(System.currentTimeMillis()), 120, "PG", 
                            (short) 2001, (byte) 7, new BigDecimal("2.99"), new BigDecimal("19.99"),
                            "Film 1", null, null, null, null, null);

        // Verificar que el método toString contiene la información correcta
        String expectedToString = "Film(filmId=1, description=Description, lastUpdate=" 
                + film.getLastUpdate() + ", length=120, rating=PG, releaseYear=2001, "
                + "rentalDuration=7, rentalRate=2.99, replacementCost=19.99, title=Film 1, "
                + "language=null, languageVO=null, filmActors=null, filmCategories=null, inventories=null)";
        
        // Asegurarse de que el valor de lastUpdate se imprima correctamente
        assertTrue(film.toString().contains(film.getLastUpdate().toString()));
        assertEquals(expectedToString, film.toString());
    }
}