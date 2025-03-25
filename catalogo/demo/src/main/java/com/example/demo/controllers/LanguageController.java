package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.DTOs.LanguageDTO;
import com.example.demo.entities.Language;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.services.LanguageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        super();
        this.languageService = languageService;
    }

    /* @GetMapping
    public List<LanguageDTO> getAll() {
        return languageService.getByProjection(LanguageDTO.class);
    } */

    @GetMapping
    public List<LanguageDTO> getAll() {
        return languageService.getAll().stream()
                .map(language -> new LanguageDTO(language.getLanguageId(), language.getName()))
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getOne(@PathVariable Integer id) throws NotFoundException{
        Language language = languageService.getOne(id)
                .orElseThrow(() -> new NotFoundException("Language not found with ID: " + id));
        return ResponseEntity.ok(new LanguageDTO(language.getLanguageId(), language.getName()));
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> add(@Valid @RequestBody LanguageDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
        Language language = new Language(
        item.getLanguageId(),
        item.getName()
        );

        language = languageService.add(language);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(language.getLanguageId()).toUri();

        return ResponseEntity.created(location).body(new LanguageDTO(language.getLanguageId(), language.getName()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody LanguageDTO item) throws BadRequestException, NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException, InvalidDataException{
        if (!id.equals(item.getLanguageId())) {
            throw new BadRequestException("The ID in the URL does not match the ID of the language");
        }
        Language existingLanguage = languageService.getOne(id)
                .orElseThrow(() -> new NotFoundException("Language not found with ID: " + id));
        
        if (item.getName() != null) {
            existingLanguage.setName(item.getName());
        }
        languageService.modify(existingLanguage);
    }

    @DeleteMapping
    public void delete(@RequestBody Language item) throws InvalidDataException {
        languageService.delete(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws InvalidDataException {
        languageService.deleteById(id);
    }

    
}
