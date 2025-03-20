package com.example.demo.services;

import java.sql.Timestamp;

import com.example.demo.DTOs.NovedadesDTO;

public interface CatalogoService {

	NovedadesDTO novedades(Timestamp fecha);

}