package com.example.demo.services.services;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.core.DomainService;
import com.example.demo.entities.Category;

public interface CategoryService extends DomainService<Category, Integer> {
	List<Category> novedades(Timestamp fecha);
}
