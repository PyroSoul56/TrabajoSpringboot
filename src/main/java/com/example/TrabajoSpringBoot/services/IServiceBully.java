package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceBully {
    public List<BullyDTO> getBullies();
    public List<BullyDTO> addBullies(List<BullyDTO> bullies);
    public Optional <BullyDTO> removeBully(String id);
    public Optional<BullyDTO> getBullyById(String id);
    public Optional<BullyDTO> getBullyByName(String name);
    public Optional<CliqueDTO> getCliqueByBullyName(String name);
    public List<RevengePlanDTO> getRevengePlansByBullyName(String name);
}
