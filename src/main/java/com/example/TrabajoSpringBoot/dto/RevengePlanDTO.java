package com.example.TrabajoSpringBoot.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public record RevengePlanDTO (

        String id,

    @NotNull(message = "Title cannot be blank")
    @Size(min = 1, max = 25)
    String title,

    @Size(min = 1, max = 500)
    String description,

    @DateTimeFormat
    LocalDate datePlanned,

    @NotNull(message = "This field cannot be blank")
    Boolean execution,

    @Enumerated
    String successLevel

){}

