package com.example.TrabajoSpringBoot.dto;

import com.example.TrabajoSpringBoot.controllers.HighSchoolRolesEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BullyDTO {

    @NotBlank
    @Size(min = 0, max = 25)
    private String name;

    @NotBlank
    @Size(min = 0, max = 25)
    private String nickname;

    @Enumerated
    private HighSchoolRolesEnum type;

    @Size(min = 0, max = 100)
    private String bullyingReason;

    @Min(0)
    @Max(10)
    private int levelOfAnnoyance;

}
