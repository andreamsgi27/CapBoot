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
		if (item == null) {
			throw new InvalidDataException("La película no puede ser nula");
		}

		categoryRepository.findById(item.getCategoryId())
				.orElseThrow(() -> new InvalidDataException("La película no existe"));

		return categoryRepository.save(item);
	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }

        categoryRepository.findById(item.getCategoryId())
                .orElseThrow(() -> new InvalidDataException("La película no existe"));

				categoryRepository.delete(item);
    }


	@Override
	public void deleteById(Integer id) throws InvalidDataException {
		if (id == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("La película no existe"));

				categoryRepository.delete(existingCategory);
    }
}
