package com.example.demo.controllers;

import java.net.URI;
import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor para inyección de dependencias
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Obtener todas las categorías
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    // Obtener una categoría por su ID
    @GetMapping("/{id}")
    public Optional<Category> getOne(@PathVariable Integer id) throws NotFoundException {
        return categoryService.getOne(id);
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Category item) throws DuplicateKeyException, InvalidDataException {
        Category newCategory = categoryService.add(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newCategory.getCategoryId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody Category item) throws BadRequestException, NotFoundException, InvalidDataException {
        if (!id.equals(item.getCategoryId())) {
            throw new BadRequestException("El ID en la URL y el ID en el cuerpo de la solicitud no coinciden.");
        }
        categoryService.modify(item);
    }

    // Eliminar una categoría por su ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws InvalidDataException {
        categoryService.deleteById(id);
    }

    // Eliminar una categoría (puedes pasar una categoría completa en el cuerpo de la solicitud)
    @DeleteMapping
    public void delete(@RequestBody Category item) throws InvalidDataException {
        categoryService.delete(item);
    }

    // Obtener categorías con novedades basadas en la fecha
    @GetMapping("/novedades")
    public List<Category> novedades(@RequestParam Timestamp fecha) {
        return categoryService.novedades(fecha);
    }
}
