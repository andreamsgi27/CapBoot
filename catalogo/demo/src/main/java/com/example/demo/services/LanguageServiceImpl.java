package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Language;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {
	
    private LanguageRepository languageRepository;

	public LanguageServiceImpl(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}

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
		if (item == null) {
			throw new InvalidDataException("El idioma no puede ser nulo");
		}

		Language existingLanguage = languageRepository.findById(item.getLanguageId()).orElseThrow(() -> new InvalidDataException("El idioma no existe"));

		existingLanguage.setName(item.getName());

		return languageRepository.save(item);
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		if (item == null) {
			throw new InvalidDataException("El idioma no puede ser nulo");
		}
		Language existingLanguage = languageRepository.findById(item.getLanguageId()).orElseThrow(() -> new InvalidDataException("El idioma no existe"));
		languageRepository.delete(existingLanguage);
	}

	@Override
	public void deleteById(Integer id) throws InvalidDataException{
		if (id == null) {
			throw new InvalidDataException("El ID no puede ser nulo");
		}
		Language existingLanguage = languageRepository.findById(id).orElseThrow(() -> new InvalidDataException("El idioma no existe"));
		languageRepository.delete(existingLanguage);
	}
}
