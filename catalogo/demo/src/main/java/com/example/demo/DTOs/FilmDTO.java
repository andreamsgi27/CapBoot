package com.example.demo.DTOs;

import java.math.BigDecimal;

import com.example.demo.entities.Film;
import com.example.demo.entities.Language;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmDTO {
    private int filmId;
    private String title;
    private Language language;
    private BigDecimal rentalRate;
    private BigDecimal replacementCost;
    private Byte rentalDuration;

    public FilmDTO(){
    }

    
    public FilmDTO from(Film source) {
            return new FilmDTO(
                                source.getFilmId(),
                                source.getTitle(),
                                source.getLanguage(),
                                source.getRentalRate(),
                                source.getReplacementCost(),
                                source.getRentalDuration()
                        );
                }

}