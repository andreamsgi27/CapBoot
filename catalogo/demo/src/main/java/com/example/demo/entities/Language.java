package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int languageId;

	@PastOrPresent(message = "La fecha de última actualización no puede ser futura")
	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	@NotBlank(message = "El nombre del idioma no puede estar vacío")
	@Size(min = 2, max = 20, message = "El nombre del idioma debe tener entre 2 y 20 caracteres")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "El nombre del idioma solo puede contener letras y espacios")
	@Column(nullable=false, length=20)
	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	private List<Film> films;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="languageVO")
	private List<Film> filmsVO;


	//Constructor
	public Language(int languageId, String name) {
		this.languageId = languageId;
		this.name = name;
	}
}