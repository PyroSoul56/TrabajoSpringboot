package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CliqueDTO (

    @NotNull(message = "Name cannot be blank")
    @Size(min = 1, max = 25)
    String name,

    @Size(min = 1, max = 100)
    String description

){}
