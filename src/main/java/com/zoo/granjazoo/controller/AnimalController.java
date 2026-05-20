package com.zoo.granjazoo.controller;

import com.zoo.granjazoo.model.Animal;
import com.zoo.granjazoo.service.AnimalService;
import com.zoo.granjazoo.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private EspecieService especieService;

    // Página de listado con filtros
    @GetMapping
    public String listar(
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String recinto,
            Model model) {
        
        // Aplicar filtros según los parámetros recibidos
        if (sexo != null && !sexo.isEmpty()) {
            model.addAttribute("animales", animalService.listarPorSexo(sexo));
            model.addAttribute("sexoSeleccionado", sexo);
        } else if (recinto != null && !recinto.isEmpty()) {
            model.addAttribute("animales", animalService.listarPorRecinto(recinto));
            model.addAttribute("recintoSeleccionado", recinto);
        } else {
            model.addAttribute("animales", animalService.listar());
        }
        
        return "animales/lista";
    }

    // Formulario para crear nuevo animal
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("especies", especieService.listar());
        return "animales/formulario";
    }

    // Guardar animal
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Animal animal,
            @RequestParam(required = false) Long especieId) {
        
        if (especieId != null) {
            animalService.guardarConEspecie(animal, especieId);
        } else {
            animalService.guardar(animal);
        }
        return "redirect:/animales";
    }

    // Formulario para editar animal
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.obtener(id));
        model.addAttribute("especies", especieService.listar());
        return "animales/formulario";
    }

    // Eliminar animal
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        animalService.eliminar(id);
        return "redirect:/animales";
    }
}