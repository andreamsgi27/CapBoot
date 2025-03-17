package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="film_category")
@NamedQuery(name="FilmCategory.findAll", query="SELECT f FROM FilmCategory f")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false, insertable=false, updatable=false)
	private Category category;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false, insertable=false, updatable=false)
	private Film film;
}