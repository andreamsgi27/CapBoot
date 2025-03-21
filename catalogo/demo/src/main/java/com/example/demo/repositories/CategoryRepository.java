package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Category;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
	List<Category> findAllByOrderByName();
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Date fecha);
}
