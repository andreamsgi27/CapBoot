package com.example.demo.DTOs;

import com.example.demo.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

//@Schema(name = "Pelicula (Corto)", description = "Version corta de las peliculas")
public record FilmShortDTO(
		@JsonProperty("id")
//		@Schema(description = "Identificador de la pelicula", accessMode = AccessMode.READ_ONLY)
		int filmId,

		@JsonProperty("titulo")
//		@Schema(description = "Titulo de la pelicula")
		String title)
{
	public static FilmShortDTO from(Film source) {
		return new FilmShortDTO(source.getFilmId(), source.getTitle());
	}

	public static Film to(FilmShortDTO source) {
		// Suponiendo que 'Film' tiene un constructor adecuado con 'filmId' y 'title'
		// Si se requieren otros campos, puedes proporcionar valores predeterminados o pasarlos como parámetros.
		
		// Ejemplo con valores predeterminados para los demás campos
		return new Film(
			source.filmId(), // mapeamos filmId del DTO a la entidad
			source.title()
		);
	}
}
