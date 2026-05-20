package com.zoo.granjazoo.service;

import com.zoo.granjazoo.model.Animal;
import com.zoo.granjazoo.model.Especie;
import com.zoo.granjazoo.repository.AnimalRepository;
import com.zoo.granjazoo.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private EspecieRepository especieRepository;

    // Obtener todos los animales
    public List<Animal> listar() {
        return animalRepository.findAll();
    }
    
    // Filtrar animales por sexo
    public List<Animal> listarPorSexo(String sexo) {
        return animalRepository.findBySexo(sexo);
    }
    
    // Filtrar animales por recinto
    public List<Animal> listarPorRecinto(String recinto) {
        return animalRepository.findByRecinto(recinto);
    }
    
    // Listar animales de una especie específica
    public List<Animal> listarPorEspecie(Long especieId) {
        return animalRepository.findByEspecieId(especieId);
    }

    // Obtener un animal por su ID
    public Animal obtener(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    // Guardar un animal sin especie
    public void guardar(Animal animal) {
        animalRepository.save(animal);
    }
    
    // Guardar un animal asociado a una especie
    public void guardarConEspecie(Animal animal, Long especieId) {
        Especie especie = especieRepository.findById(especieId).orElse(null);
        animal.setEspecie(especie);
        animalRepository.save(animal);
    }

    // Eliminar un animal por su ID
    public void eliminar(Long id) {
        animalRepository.deleteById(id);
    }
}