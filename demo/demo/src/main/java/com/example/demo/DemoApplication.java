package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.unpaquete.Rango;
import com.example.demo.unpaquete.Repositorio;
import com.example.demo.unpaquete.Servicio;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.ioc")
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
//	@Autowired //(required = false)
//	Servicio srv;
	
	@Autowired //(required = false)
//	@Qualifier("verdad")
	Repositorio repo1;
	@Autowired //(required = false)
//	@Qualifier("mentira")
	Repositorio repo2;
//	@Autowired //(required = false)
//	Repositorio repo;

	@Value("${mi.valor:valor por defecto}")
	String valor;
	
	@Autowired
	Rango rango;
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		ejemplosIOC();
	}
	
	private void ejemplosIOC() {
		//Servicio srv = new Servicio(new Repositorio(new Configuracion()));
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

//		srv.guardar();
//		repo.guardar();
		repo1.guardar();
		repo2.guardar();
		System.err.println("Valor: " + valor);
		System.err.println("Rango: " + rango);
	}
	
//	@Bean
//  	CommandLineRunner demo() {
//		return (args) -> {
//			System.err.println("Aplicacion arrancadaaaaaaaaaaaaaa");
//		};
//	}

}