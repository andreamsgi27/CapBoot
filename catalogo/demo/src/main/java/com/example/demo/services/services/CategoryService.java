package com.example.demo.services.services;

import java.util.Date;
import java.util.List;

import com.example.demo.core.DomainService;
import com.example.demo.entities.Category;

public interface CategoryService extends DomainService<Category, Integer> {
	List<Category> novedades(Date fecha);
}
