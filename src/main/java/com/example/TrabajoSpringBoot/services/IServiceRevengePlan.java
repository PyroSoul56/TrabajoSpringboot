package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;

import java.util.List;
import java.util.Optional;

public interface IServiceRevengePlan {
    public List<RevengePlan> getRevengePlan();
    public RevengePlan addRevengePlan(RevengePlan r);
    public Optional<RevengePlan> removeRevengePlan(String id);
    public Optional<RevengePlan> getRevengePlanById(String id);
    public Optional<RevengePlan> getRevengePlanByTitle(String name);
    public List<RevengePlan> getExecutedRevengePlansByExecution(boolean executed);
    public List<Media> getMediaByRevengePlanId(int id);
}
