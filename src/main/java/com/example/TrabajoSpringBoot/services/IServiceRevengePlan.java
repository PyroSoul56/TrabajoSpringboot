package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;

import java.util.List;
import java.util.Optional;

public interface IServiceRevengePlan {
    public List<RevengePlanDTO> getRevengePlan();
    public List<RevengePlanDTO> addRevengePlans(List<RevengePlanDTO> revengePlans);
    public Optional<RevengePlanDTO> removeRevengePlan(String id);
    public Optional<RevengePlanDTO> getRevengePlanById(String id);
    public Optional<RevengePlanDTO> getRevengePlanByTitle(String name);
    public List<RevengePlanDTO> findRevengePlanByExecutionIs(boolean executed);
    public List<MediaDTO> getMediaByRevengePlanTitle(String title);
}
