package com.example.demo.controllers;

import java.net.URI;
import java.util.Date;
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

import com.example.demo.entities.Actor;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.services.ActorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    // Constructor para la inyección de dependencias
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // Obtener todos los actores
    @GetMapping
    public List<Actor> getAll() {
        return actorService.getAll();
    }

    // Obtener un actor por su ID
    @GetMapping("/{id}")
    public Optional<Actor> getOne(@PathVariable Integer id) throws NotFoundException {
        return actorService.getOne(id);
    }

    // Crear un nuevo actor
    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Actor item) throws DuplicateKeyException, InvalidDataException {
        Actor newActor = actorService.add(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newActor.getActorId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Actualizar un actor existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody Actor item) throws BadRequestException, NotFoundException, InvalidDataException {
        if (!id.equals(item.getActorId())) {
            throw new BadRequestException("El ID en la URL y el ID en el cuerpo de la solicitud no coinciden.");
        }
        actorService.modify(item);
    }

    // Eliminar un actor por su ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws InvalidDataException {
        actorService.deleteById(id);
    }

    // Eliminar un actor (si se pasa un actor nulo, lo eliminaría)
    @DeleteMapping
    public void delete(@RequestBody Actor item) throws InvalidDataException {
        actorService.delete(item);
    }

    // Aquí iría una lógica de negocio adicional como repartir premios o lo que sea
    @PostMapping("/repartir-premios")
    public void repartirPremios() {
        actorService.repartePremios();
    }

    // Obtener actores con novedades (basado en la fecha)
    @GetMapping("/novedades")
    public List<Actor> novedades(@RequestParam Date fecha) {
        return actorService.novedades(fecha);
    }
}