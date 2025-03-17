package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the language database table.
 * 
 */
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

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

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