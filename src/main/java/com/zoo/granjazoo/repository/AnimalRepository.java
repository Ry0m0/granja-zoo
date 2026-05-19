package com.zoo.granjazoo.repository;

import com.zoo.granjazoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Filtrar animales por sexo
    List<Animal> findBySexo(String sexo);

    // Filtrar animales por recinto
    List<Animal> findByRecinto(String recinto);

    // Filtrar animales por rango de fechas de nacimiento
    List<Animal> findByFechaNacimientoBetween(LocalDate startDate, LocalDate endDate);

    // Filtrar animales por especie
    List<Animal> findByEspecieId(Long especieId);

    // Contar animales por especie
    long countByEspecieId(Long especieId);

    // Calcular peso promedio por especie (usando JPQL)
    @Query("SELECT AVG(a.peso) FROM Animal a WHERE a.especie.id = :especieId")
    Double findAverageWeightByEspecieId(@Param("especieId") Long especieId);
}