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
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	//validaciones
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-Za-z]+$", message = "El nombre debe contener solo letras")
	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@NotBlank(message = "El apellido no puede estar vacío")
	@Size(min = 2, max = 45, message = "El apellido debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-Za-z]+$", message = "El apellido debe contener solo letras")
	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@PastOrPresent(message = "La fecha de última actualización no puede ser futura")
	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor")
	private List<FilmActor> filmActors;

	public Actor(int actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}
}