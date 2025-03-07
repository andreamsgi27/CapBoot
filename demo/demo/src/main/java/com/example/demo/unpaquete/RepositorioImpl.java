package com.example.demo.unpaquete;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioImpl implements Repositorio {
	public RepositorioImpl(Configuration config, Registro registro) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void guardar() {
		System.err.println("Guardando");
	}
}