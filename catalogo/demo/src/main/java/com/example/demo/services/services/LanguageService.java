package com.example.demo.services.services;

import java.util.Date;
import java.util.List;

import com.example.demo.core.DomainService;
import com.example.demo.entities.Language;

public interface LanguageService extends DomainService<Language, Integer> {
	List<Language> novedades(Date fecha);
}