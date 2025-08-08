package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

public record MoodTrackerDTO (

        String id,

    @NotBlank
    @Max(10)
    @Min(0)
    int moodLevel,

    @Size(min = 0, max = 100)
    String note,

    @CreationTimestamp
    LocalDate date

){}
