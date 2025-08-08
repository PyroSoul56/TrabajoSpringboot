package com.example.TrabajoSpringBoot.models;

import com.example.TrabajoSpringBoot.controllers.HighSchoolRolesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity()
@Table(name="bullies")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bully {
    //TODO: Implement the Bully class

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
