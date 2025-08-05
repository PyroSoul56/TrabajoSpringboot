package com.example.TrabajoSpringBoot.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class RevengePlanDTO {

    @NotNull(message = "Title cannot be blank")
    @Size(min = 1, max = 25)
    private String title;

    @Size(min = 1, max = 500)
    private String description;

    @DateTimeFormat
    private LocalDate datePlanned;

    @NotNull(message = "This field cannot be blank")
    private Boolean isExecuted;

    @Enumerated
    private String successLevel;


}
