package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.dto.CliqueDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceClique {
    public List<CliqueDTO> getCliques();
    public List<CliqueDTO> addCliques(List<CliqueDTO> cliques);
    public Optional<CliqueDTO> removeClique(String id);
    public Optional<CliqueDTO> getCliqueById(String id);
    public Optional<CliqueDTO> getCliqueByName(String name);
    public List<BullyDTO> getBulliesByCliqueName(String id);
    public Optional<CliqueDTO> setBullies(String cliqueName, List<String> bullyNames);
}
