package com.example.demo.unpaquete;

import org.springframework.stereotype.Service;

@Service
public class ServicioImpl implements Servicio {
	private Repositorio repositorio;

	public ServicioImpl(/*@Qualifier("verdad")*/ Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void guardar() {
		// ...
		repositorio.guardar();
	}
}