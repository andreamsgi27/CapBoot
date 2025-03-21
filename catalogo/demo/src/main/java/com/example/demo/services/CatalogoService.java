package com.example.demo.services;

import java.util.Date;

import com.example.demo.DTOs.NovedadesDTO;

public interface CatalogoService {

	NovedadesDTO novedades(Date fecha);

}