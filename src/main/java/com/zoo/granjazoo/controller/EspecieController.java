package com.zoo.granjazoo.controller;

import com.zoo.granjazoo.model.Especie;
import com.zoo.granjazoo.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/especies")
public class EspecieController {

    @Autowired
    private EspecieService service;

    // Página de listado
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("especies", service.listar());
        return "especies/lista";
    }

    // Formulario para crear nueva especie
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("especie", new Especie());
        return "especies/formulario";
    }

    // Guardar especie (crear o actualizar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Especie especie) {
        service.guardar(especie);
        return "redirect:/especies";
    }

    // Formulario para editar especie existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("especie", service.obtener(id));
        return "especies/formulario";
    }

    // Eliminar especie
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/especies";
    }
}