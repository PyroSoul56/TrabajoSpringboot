package com.example.TrabajoSpringBoot.models;

import com.example.TrabajoSpringBoot.controllers.SuccessLevelEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Entity()
@Table(name="revenge_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RevengePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bully_id", nullable = false)
    private Bully bully;

    @Column(nullable = false)
    private LocalDate datePlanned;

    @Column(nullable = false)
    private boolean execution;

    @Enumerated
    private SuccessLevelEnum successLevel;

    @OneToMany(mappedBy = "revengePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Media> relatedMedia;

}

