package com.example.demo.controllers;

import java.net.URI;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.DTOs.CategoryDTO;
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
    public List<CategoryDTO> getAll() {
        return categoryService.getAll().stream()
                .map(category -> new CategoryDTO(category.getCategoryId(), category.getName()))
                .toList();
    }

    // Obtener una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable Integer id) throws NotFoundException {
        // Buscar la categoría por su ID usando el servicio
        Category category = categoryService.getOne(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada con ID: " + id));

        // Devolver la respuesta con el DTO de la categoría encontrada
        return ResponseEntity.ok(new CategoryDTO(category.getCategoryId(), category.getName()));
    }
    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoryDTO> add(@Valid @RequestBody CategoryDTO item) throws DuplicateKeyException, InvalidDataException {
        // Convertir CategoryDTO a Category
        Category category = new Category(
                item.getCategoryId(),
                item.getName()
        );

        // Guardar la entidad Category utilizando el servicio
        category = categoryService.add(category);

        // Crear la URI para la nueva entidad Category
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getCategoryId())
                .toUri();

        // Devolver la respuesta con el DTO CategoryDTO
        return ResponseEntity.created(location)
                .body(new CategoryDTO(category.getCategoryId(), category.getName()));
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
        if (!id.equals(item.getCategoryId())) {
            throw new BadRequestException("El ID en la URL y el ID en el cuerpo de la solicitud no coinciden.");
        }

        // Obtener la entidad Category actual desde la base de datos
        Category existingCategory = categoryService.getOne(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada."));

        // Actualizar solo los campos proporcionados en el cuerpo de la solicitud
        if (item.getName() != null) {
            existingCategory.setName(item.getName());
        }

        // Modificar la entidad Category utilizando el servicio
        categoryService.modify(existingCategory);
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
    public List<Category> novedades(@RequestParam Date fecha) {
        return categoryService.novedades(fecha);
    }
}
