package com.example.TrabajoSpringBoot.dto;

import com.example.TrabajoSpringBoot.controllers.FileTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MediaDTO {


    @NotBlank(message = "Name cannot be blank")
    @Enumerated
    private FileTypeEnum type;

    @NotBlank(message = "URL cannot be blank")
    private String url;

    @Size(min = 0, max = 100)
    private String caption;


}
