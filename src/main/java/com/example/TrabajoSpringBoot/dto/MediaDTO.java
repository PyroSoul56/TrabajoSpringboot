package com.example.TrabajoSpringBoot.dto;

import com.example.TrabajoSpringBoot.controllers.FileTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MediaDTO (

        String id,

    @Enumerated
    @jakarta.validation.constraints.NotNull(message = "Type cannot be null")
    FileTypeEnum type,

    @NotBlank(message = "URL cannot be blank")
    String url,

    @Size(min = 0, max = 100)
    String caption,

    String revengePlanTitle
){}
