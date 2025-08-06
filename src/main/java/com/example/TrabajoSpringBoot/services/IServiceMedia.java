package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;

import java.util.List;
import java.util.Optional;

public interface IServiceMedia {
    public List<MediaDTO> getMedia();
    public Media addMedia(MediaDTO m, String revengePlanId);
    public Optional<Media> removeMedia(String id);
    public Optional<Media> getMediaById(String id);
    public RevengePlan getRevengePlanByMediaId(String id);
}
