package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Actor;
import com.example.demo.repositories.ActorRepository;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Catalogo arrancado");
		updatearDatos();
		eliminarDatos();
		añadirDatos();
		verDatos();
	}

	@Autowired
	private ActorRepository actorRepository;

		//Muestra por terminal lista de actores
	private void verDatos(){
		actorRepository.findAll().forEach(System.err::println);
	}

		//Añade un actor a la lista
	private void añadirDatos(){
		var actor = new Actor(0, "Juan", "Perez");
		actorRepository.save(actor);
		actorRepository.findAll().forEach(System.err::println);
	}

		//Updatea una parte del actor
	private void updatearDatos(){
		var item = actorRepository.findById(45);
		if (item.isPresent()){
			var actor = item.get();
			actor.setFirstName("Pepito");
			actor.setLastName(actor.getLastName().toUpperCase());//coge el que ya había
			actorRepository.save(actor);
		} else {
			System.err.println("No existe el actor");
		}
	}

	//Eliminar datos
	private void eliminarDatos(){
		actorRepository.deleteById(45);
	}

}