package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<LanguageDTO> getAll() {
        return languageService.getByProjection(LanguageDTO.class);
    }

    @GetMapping("/{id}")
    public Optional<Language> getOne(@PathVariable int id) throws NotFoundException{
        return languageService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody LanguageDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
        var newItem = languageService.add(LanguageDTO.from(item));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newItem.getLanguageId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody LanguageDTO item) throws BadRequestException, NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException, InvalidDataException{
        if (item.getLanguageId() != id) {
            throw new BadRequestException("The ID in the URL and the request body are different");
        }
        languageService.modify(LanguageDTO.from(item));
    }

    @DeleteMapping
    public void delete() throws InvalidDataException {
        languageService.delete(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws InvalidDataException {
        languageService.deleteById(id);
    }

    
}
