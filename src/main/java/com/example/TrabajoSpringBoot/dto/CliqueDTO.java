package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record CliqueDTO (

        String id,

    @NotNull(message = "Name cannot be blank")
    @Size(min = 1, max = 25)
    String name,

    @Size(min = 1, max = 100)
    String description,

    List<String> bullyNames
){}
