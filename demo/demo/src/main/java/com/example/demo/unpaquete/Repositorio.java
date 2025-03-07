package com.example.demo.unpaquete;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("verdad")
public interface Repositorio {

	void guardar();

}