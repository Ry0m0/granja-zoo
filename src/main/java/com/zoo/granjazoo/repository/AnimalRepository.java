package com.zoo.granjazoo.repository;

import com.zoo.granjazoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    // Método para filtrar animales por sexo
    List<Animal> findBySexo(String sexo);
    
    // Método para filtrar animales por recinto
    List<Animal> findByRecinto(String recinto);
    
    // Método para listar animales de una especie específica
    List<Animal> findByEspecieId(Long especieId);
}