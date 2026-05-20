package com.zoo.granjazoo.service;

import com.zoo.granjazoo.model.Especie;
import com.zoo.granjazoo.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository repository;

    // Obtener todas las especies
    public List<Especie> listar() {
        return repository.findAll();
    }

    // Obtener una especie por su ID
    public Especie obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Guardar una especie (nueva o actualizada)
    public void guardar(Especie especie) {
        repository.save(especie);
    }

    // Eliminar una especie por su ID
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}