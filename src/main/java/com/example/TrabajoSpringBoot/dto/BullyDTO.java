package com.example.TrabajoSpringBoot.dto;

import com.example.TrabajoSpringBoot.controllers.HighSchoolRolesEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BullyDTO (

    String id,

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 0, max = 25)
    String name,

    @NotBlank(message = "Nickname cannot be blank")
    @Size(min = 0, max = 25)
    String nickname,

    @Enumerated
    HighSchoolRolesEnum highSchoolRole,

    @Size(min = 0, max = 100)
    String bullyingReason,

    @Min(0)
    @Max(10)
    int levelOfAnnoyance


){}
