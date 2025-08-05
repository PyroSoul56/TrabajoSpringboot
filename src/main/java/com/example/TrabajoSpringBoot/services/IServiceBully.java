package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
import com.example.TrabajoSpringBoot.models.RevengePlan;

import java.util.List;
import java.util.Optional;

public interface IServiceBully {
    public List<BullyDTO> getBullies();
    public Bully addBully(BullyDTO b);
    public Optional <Bully> removeBully(int id);
    public Optional<Bully> getBullyById(int id);
    public Optional<Bully> getBullyByName(String name);
    public Optional<Clique> getCliqueByBullyId(int id);
    public List<RevengePlan> getRevengePlansByBullyId();
}
