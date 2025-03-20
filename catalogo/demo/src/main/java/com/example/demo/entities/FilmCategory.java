package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="film_category")
@NamedQuery(name="FilmCategory.findAll", query="SELECT f FROM FilmCategory f")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id;

	@Column(name="last_update", insertable = false, updatable = false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id", insertable=false, updatable=false)
	@JsonManagedReference
	private Category category;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", insertable=false, updatable=false)
	@JsonManagedReference
	private Film film;

	public FilmCategory() {
	}

	public FilmCategory(Film film, Category category) {
		this.film = film;
		this.category = category;
	}

	public FilmCategoryPK getId() {
		return this.id;
	}

	public void setId(FilmCategoryPK id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	@PrePersist
	@PreUpdate
	void prePersiste() {
//		System.err.println("prePersiste(): Bug Hibernate");
		if (id == null) {
			setId(new FilmCategoryPK(film.getFilmId(), category.getCategoryId()));
		}
	}

}