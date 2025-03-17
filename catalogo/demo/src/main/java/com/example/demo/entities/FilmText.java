package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="film_text")
@NamedQuery(name="FilmText.findAll", query="SELECT f FROM FilmText f")
public class FilmText implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	private short filmId;

	@Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres")
	@Lob
	private String description;

	@NotBlank(message = "El título no puede estar vacío")
	@Size(max = 255, message = "El título no puede tener más de 255 caracteres")
	@Column(nullable=false, length=255)
	private String title;
}