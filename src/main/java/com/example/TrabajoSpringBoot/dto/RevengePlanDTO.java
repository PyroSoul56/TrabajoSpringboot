package com.example.TrabajoSpringBoot.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RevengePlanDTO {

    @NotNull
    @Size(min = 1, max = 25)
    private String title;

    @Size(min = 1, max = 500)
    private String description;

    @DateTimeFormat
    private LocalDate datePlanned;

    @NotNull
    private Boolean isExecuted;

    @Enumerated
    private String successLevel;


}
