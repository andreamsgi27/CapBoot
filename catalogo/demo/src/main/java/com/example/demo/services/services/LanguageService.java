package com.example.demo.services.services;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.core.DomainService;
import com.example.demo.entities.Language;

public interface LanguageService extends DomainService<Language, Integer> {
	List<Language> novedades(Timestamp fecha);
}