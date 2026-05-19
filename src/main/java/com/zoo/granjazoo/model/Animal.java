package com.zoo.granjazoo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "animales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;              // Nombre del animal

    private String sexo;                // Sexo: M o H

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;  // Fecha de nacimiento

    private Double peso;                // Peso en kilogramos

    private String recinto;             // Recinto donde vive

    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;            // Especie a la que pertenece
}