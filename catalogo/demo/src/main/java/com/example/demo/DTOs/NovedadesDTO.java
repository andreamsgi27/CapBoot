package com.example.demo.DTOs;

import java.util.List;

import com.example.demo.entities.Category;
import com.example.demo.entities.Language;
import com.example.demo.DTOs.ActorDTO;
import com.example.demo.DTOs.FilmDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovedadesDTO {
	private List<FilmDTO> films;
	private List<ActorDTO> actors;
	private List<Category> categories;
	private List<Language> languages;
}
