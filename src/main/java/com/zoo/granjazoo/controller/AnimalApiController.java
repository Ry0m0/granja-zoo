package com.zoo.granjazoo.controller;

import com.zoo.granjazoo.model.Animal;
import com.zoo.granjazoo.model.Especie;
import com.zoo.granjazoo.service.AnimalService;
import com.zoo.granjazoo.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class AnimalApiController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private EspecieService especieService;

    // GET /api/animales
    // GET /api/animales?sexo=M
    // GET /api/animales?recinto=ZonaNorte
    // GET /api/animales?especieId=1
    @GetMapping
    public List<Animal> listar(
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String recinto,
            @RequestParam(required = false) Long especieId) {
        if (sexo != null && !sexo.isBlank()) return animalService.listarPorSexo(sexo);
        if (recinto != null && !recinto.isBlank()) return animalService.listarPorRecinto(recinto);
        if (especieId != null) return animalService.listarPorEspecie(especieId);
        return animalService.listar();
    }

    // GET /api/animales/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Animal> obtener(@PathVariable Long id) {
        Animal animal = animalService.obtener(id);
        if (animal == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(animal);
    }

    // POST /api/animales
    @PostMapping
    public ResponseEntity<Animal> crear(@RequestBody Animal animal) {
        if (animal.getEspecie() != null && animal.getEspecie().getId() != null) {
            Especie especie = especieService.obtener(animal.getEspecie().getId());
            animal.setEspecie(especie);
        }
        animalService.guardar(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }

    // PUT /api/animales/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizar(@PathVariable Long id, @RequestBody Animal animal) {
        Animal existente = animalService.obtener(id);
        if (existente == null) return ResponseEntity.notFound().build();
        existente.setNombre(animal.getNombre());
        existente.setSexo(animal.getSexo());
        existente.setFechaNacimiento(animal.getFechaNacimiento());
        existente.setPeso(animal.getPeso());
        existente.setRecinto(animal.getRecinto());
        if (animal.getEspecie() != null && animal.getEspecie().getId() != null) {
            existente.setEspecie(especieService.obtener(animal.getEspecie().getId()));
        }
        animalService.guardar(existente);
        return ResponseEntity.ok(existente);
    }

    // PATCH /api/animales/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Animal> actualizarParcial(@PathVariable Long id, @RequestBody Animal animal) {
        Animal existente = animalService.obtener(id);
        if (existente == null) return ResponseEntity.notFound().build();
        if (animal.getNombre() != null) existente.setNombre(animal.getNombre());
        if (animal.getSexo() != null) existente.setSexo(animal.getSexo());
        if (animal.getFechaNacimiento() != null) existente.setFechaNacimiento(animal.getFechaNacimiento());
        if (animal.getPeso() != null) existente.setPeso(animal.getPeso());
        if (animal.getRecinto() != null) existente.setRecinto(animal.getRecinto());
        if (animal.getEspecie() != null && animal.getEspecie().getId() != null) {
            existente.setEspecie(especieService.obtener(animal.getEspecie().getId()));
        }
        animalService.guardar(existente);
        return ResponseEntity.ok(existente);
    }

    // DELETE /api/animales/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (animalService.obtener(id) == null) return ResponseEntity.notFound().build();
        animalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}