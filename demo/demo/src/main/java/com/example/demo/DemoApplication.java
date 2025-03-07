package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.unpaquete.Repositorio;
import com.example.demo.unpaquete.Servicio;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	@Autowired
	Servicio srv;

	@Autowired
	@Qualifier("verdad")
	Repositorio repo1;

	@Autowired
	@Qualifier("mentira")
	Repositorio repo2;
	


	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		//Servicio srv = new Servicio(new Repositorio(new Configuracion()));
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		//srv.guardar();
		repo1.guardar();
		repo2.guardar();
	}
}
