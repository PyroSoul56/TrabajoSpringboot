package com.example.TrabajoSpringBoot.models;

import com.example.TrabajoSpringBoot.controllers.FileTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name="media")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "revenge_plan_id", nullable = false)
    private RevengePlan revengePlan;

    @Column(nullable = false)
    private FileTypeEnum type;

    @Column(nullable = false)
    private String url;

    private String caption;

}
