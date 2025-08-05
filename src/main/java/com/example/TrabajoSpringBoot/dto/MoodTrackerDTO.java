package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class MoodTrackerDTO {

    @NotBlank
    @Max(10)
    @Min(0)
    private int moodLevel;

}
