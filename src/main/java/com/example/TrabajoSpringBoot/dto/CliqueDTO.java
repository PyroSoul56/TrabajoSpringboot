package com.example.TrabajoSpringBoot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CliqueDTO {

    @NotNull
    @Size(min = 1, max = 25)
    private String name;

    @Size(min = 1, max = 100)
    private String description;

}
