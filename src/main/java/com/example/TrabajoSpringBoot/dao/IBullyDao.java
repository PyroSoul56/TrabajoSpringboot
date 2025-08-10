package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBullyDao  extends JpaRepository <Bully, String> {

    Optional<Bully> findByName(String name);
    Optional<Bully> findByNickname(String nickname);

    List<Bully> findByCliqueName(String cliqueName);
}
