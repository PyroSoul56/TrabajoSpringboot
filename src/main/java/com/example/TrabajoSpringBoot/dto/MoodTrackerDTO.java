package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MoodTrackerDTO {

    @NotBlank
    @Max(10)
    @Min(0)
    private int moodLevel;

    @Size(min = 0, max = 100)
    private String note;

}
