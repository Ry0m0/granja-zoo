package com.zoo.granjazoo.controller;

import com.zoo.granjazoo.model.Especie;
import com.zoo.granjazoo.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
public class EspecieApiController {

    @Autowired
    private EspecieService service;

    // GET /api/especies
    // GET /api/especies?nombre=Leon
    @GetMapping
    public List<Especie> listar(@RequestParam(required = false) String nombre) {
        List<Especie> todas = service.listar();
        if (nombre != null && !nombre.isBlank()) {
            return todas.stream()
                    .filter(e -> e.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .toList();
        }
        return todas;
    }

    // GET /api/especies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Especie> obtener(@PathVariable Long id) {
        Especie especie = service.obtener(id);
        if (especie == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(especie);
    }

    // POST /api/especies
    @PostMapping
    public ResponseEntity<Especie> crear(@RequestBody Especie especie) {
        service.guardar(especie);
        return ResponseEntity.status(HttpStatus.CREATED).body(especie);
    }

    // PUT /api/especies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Especie> actualizar(@PathVariable Long id, @RequestBody Especie especie) {
        Especie existente = service.obtener(id);
        if (existente == null) return ResponseEntity.notFound().build();
        existente.setNombre(especie.getNombre());
        existente.setReino(especie.getReino());
        existente.setHabitat(especie.getHabitat());
        existente.setPeligro(especie.getPeligro());
        service.guardar(existente);
        return ResponseEntity.ok(existente);
    }

    // PATCH /api/especies/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Especie> actualizarParcial(@PathVariable Long id, @RequestBody Especie especie) {
        Especie existente = service.obtener(id);
        if (existente == null) return ResponseEntity.notFound().build();
        if (especie.getNombre() != null) existente.setNombre(especie.getNombre());
        if (especie.getReino() != null) existente.setReino(especie.getReino());
        if (especie.getHabitat() != null) existente.setHabitat(especie.getHabitat());
        if (especie.getPeligro() != null) existente.setPeligro(especie.getPeligro());
        service.guardar(existente);
        return ResponseEntity.ok(existente);
    }

    // DELETE /api/especies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtener(id) == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}