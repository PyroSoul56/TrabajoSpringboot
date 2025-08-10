package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceMedia {
    public List<MediaDTO> getMedia();
    public List<MediaDTO> addMedia(List<MediaDTO> mediaDTOs);
    public Optional<MediaDTO> removeMedia(String id);
    public Optional<MediaDTO> getMediaById(String id);
    public Optional<RevengePlanDTO> getRevengePlanByMediaId(String id);
}
