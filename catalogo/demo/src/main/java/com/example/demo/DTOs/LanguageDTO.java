package com.example.demo.DTOs;

import java.io.Serializable;

import com.example.demo.entities.Language;

public class LanguageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int languageId;
    //private Date lastUpdate;
    private String name;
    /* private List<FilmDTO> films;
    private List<FilmDTO> filmsVO; */

    // Constructor
    /* public LanguageDTO(int languageId, Date lastUpdate, String name, List<FilmDTO> films, List<FilmDTO> filmsVO) {
        this.languageId = languageId;
        this.lastUpdate = lastUpdate;
        this.name = name;
        this.films = films;
        this.filmsVO = filmsVO;
    }
 */
    public LanguageDTO(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public static Language from(LanguageDTO dto) {
        Language language = new Language();
        // Map fields from dto to language entity
        // Example: language.setName(dto.getName());
        return language;
    }

    // Getters y Setters
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /* public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    } */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/* 
    public List<FilmDTO> getFilms() {
        return films;
    }

    public void setFilms(List<FilmDTO> films) {
        this.films = films;
    }

    public List<FilmDTO> getFilmsVO() {
        return filmsVO;
    }

    public void setFilmsVO(List<FilmDTO> filmsVO) {
        this.filmsVO = filmsVO;
    } */
}
