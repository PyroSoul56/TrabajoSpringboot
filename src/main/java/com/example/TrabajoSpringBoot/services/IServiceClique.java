package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;

import java.util.List;
import java.util.Optional;

public interface IServiceClique {
    public List<CliqueDTO> getCliques();
    public Clique addClique(CliqueDTO c);
    public Optional<Clique> removeClique(int id);
    public Optional<Clique> getCliqueById(int id);
    public Optional<Clique> getCliqueByName(String name);
    public List<Bully> getBulliesByCliqueId(int id);
}
