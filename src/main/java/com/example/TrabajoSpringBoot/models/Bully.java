package com.example.TrabajoSpringBoot.models;

import com.example.TrabajoSpringBoot.controllers.HighSchoolRolesEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity()
@Table(name="bullies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bully {
    //TODO: Implement the Bully class

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private HighSchoolRolesEnum highSchoolRole;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "clique_id")
    private Clique clique;

    private String bullyingReason;

    private int levelOfAnnoyance;

    @OneToMany(mappedBy = "bully", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RevengePlan> revengePlans;







}
