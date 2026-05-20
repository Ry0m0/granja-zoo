package com.zoo.granjazoo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;      // Nombre de la especie
    private String reino;       // Reino (animal, vegetal)
    private String habitat;     // Hábitat natural
    private Boolean peligro;    // En peligro de extinción
    
    // Relación OneToMany: una especie tiene muchos animales
    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL)
    private List<Animal> animales = new ArrayList<>();

    // Constructor vacío (necesario para JPA)
    public Especie() {}
    
    // Constructor con parámetros
    public Especie(String nombre, String reino, String habitat, Boolean peligro) {
        this.nombre = nombre;
        this.reino = reino;
        this.habitat = habitat;
        this.peligro = peligro;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getReino() { return reino; }
    public void setReino(String reino) { this.reino = reino; }
    
    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }
    
    public Boolean getPeligro() { return peligro; }
    public void setPeligro(Boolean peligro) { this.peligro = peligro; }
    
    public List<Animal> getAnimales() { return animales; }
    public void setAnimales(List<Animal> animales) { this.animales = animales; }
}