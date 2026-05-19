package com.zoo.granjazoo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "especies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;              // Nombre de la especie

    private String reino;               // Reino (animal, vegetal, etc.)

    private String habitat;             // Habitat natural

    @Column(name = "en_peligro")
    private Boolean enPeligroExtincion; // En peligro de extincion o no

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animales = new ArrayList<>();

    // Metodo auxiliar para agregar un animal
    public void addAnimal(Animal animal) {
        animales.add(animal);
        animal.setEspecie(this);
    }

    // Metodo auxiliar para eliminar un animal
    public void removeAnimal(Animal animal) {
        animales.remove(animal);
        animal.setEspecie(null);
    }
}