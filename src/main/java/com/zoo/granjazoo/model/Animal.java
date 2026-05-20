package com.zoo.granjazoo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;              // Nombre del animal
    private String sexo;                // Sexo: M (macho) o H (hembra)
    private LocalDate fechaNacimiento;  // Fecha de nacimiento
    private Double peso;                // Peso en kilogramos
    private String recinto;             // Recinto donde vive
    
    // Relación ManyToOne: muchos animales pertenecen a una especie
    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;

    // Constructor vacío
    public Animal() {}
    
    // Constructor con parámetros
    public Animal(String nombre, String sexo, LocalDate fechaNacimiento, Double peso, String recinto) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.recinto = recinto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    
    public String getRecinto() { return recinto; }
    public void setRecinto(String recinto) { this.recinto = recinto; }
    
    public Especie getEspecie() { return especie; }
    public void setEspecie(Especie especie) { this.especie = especie; }
}