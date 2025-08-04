package com.example.TrabajoSpringBoot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity()
@Table(name="cliques")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Clique {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "clique", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bully> bullies;

}
