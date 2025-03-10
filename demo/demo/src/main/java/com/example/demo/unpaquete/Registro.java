package com.example.demo.unpaquete;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Registro {
	public Registro(Configuracion config) {
		this.config = config;
	}

	private Configuracion config;
	
//	public Registro() {
//		System.err.println("Registro creado");
//	}
	
	@PostConstruct
	private void init() {
		config.init();
	}
}