package com.example.demo.services;

import java.util.Date;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTOs.ActorDTO;
import com.example.demo.DTOs.FilmDTO;
import com.example.demo.DTOs.NovedadesDTO;
import com.example.demo.services.services.ActorService;
import com.example.demo.services.services.CategoryService;
import com.example.demo.services.services.FilmService;
import com.example.demo.services.services.LanguageService;

@Service
public class CatalogoServiceImpl implements CatalogoService {
	@Autowired
	private FilmService filmSrv;
	@Autowired
	private ActorService artorSrv;
	@Autowired
	private CategoryService categorySrv;
	@Autowired
	private LanguageService languageSrv;

	@Override
	public NovedadesDTO novedades(Date fecha) {
		// Date fecha = Date.valueOf("2019-01-01 00:00:00");
		if(fecha == null)
			fecha = Date.from(Instant.now().minusSeconds(36000));
		return new NovedadesDTO(
				filmSrv.novedades(fecha).stream().map(item -> new FilmDTO()).toList(), 
				artorSrv.novedades(fecha).stream().map(item -> ActorDTO.from(item)).toList(), 
				categorySrv.novedades(fecha),
				languageSrv.novedades(fecha)
				);
	}

}
