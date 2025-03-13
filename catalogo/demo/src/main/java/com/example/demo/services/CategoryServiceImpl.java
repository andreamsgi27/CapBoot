package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
        }

    @Override
    public Optional<Category> getOne(Integer id) {
        return categoryRepository.findById(id);
	}

    @Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("No puede ser nulo");
		}
		if(item.getCategoryId() > 0 && categoryRepository.existsById(item.getCategoryId())) {
			throw new DuplicateKeyException("Ya existe");
		}
		return categoryRepository.save(item);
	}

    @Override
	public Category modify(Category item) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}
}
