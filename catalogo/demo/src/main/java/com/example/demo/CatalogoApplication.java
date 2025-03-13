package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repositories.ActorRepository;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Catalogo arrancado");
		ejemploDatos();
	}

	@Autowired
	private ActorRepository actorRepository;

	private void ejemploDatos(){
		actorRepository.findAll().forEach(System.err::println);
	}

}