package com.zoo.granjazoo.repository;

import com.zoo.granjazoo.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    // Metodos CRUD basicos proporcionados por JpaRepository
}