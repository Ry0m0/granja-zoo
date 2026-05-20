package com.zoo.granjazoo.repository;

import com.zoo.granjazoo.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 
public interface EspecieRepository extends JpaRepository<Especie, Long> {

}