package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.Clique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICliqueDao extends JpaRepository<Clique, String> {
    Optional<Clique> findByName(String name);
}
