package com.example.TrabajoSpringBoot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

@Entity()
@Table(name="mood_tracker")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MoodTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    private LocalDate date;

    @Column(nullable = false)
    private int moodLevel;

    private String note;

}
