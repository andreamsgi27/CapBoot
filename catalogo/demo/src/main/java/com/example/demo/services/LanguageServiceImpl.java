package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Language;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.LanguageRepository;

public class LanguageServiceImpl implements LanguageService {
    private LanguageRepository languageRepository;

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
        }

    @Override
    public Optional<Language> getOne(Integer id) {
        return languageRepository.findById(id);
	}

    @Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("No puede ser nulo");
		}
		if(item.getLanguageId() > 0 && languageRepository.existsById(item.getLanguageId())) {
			throw new DuplicateKeyException("Ya existe");
		}
		return languageRepository.save(item);
	}

    @Override
	public Language modify(Language item) throws InvalidDataException {
		
		//return null;
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}
}
