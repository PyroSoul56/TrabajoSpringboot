package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMediaDao extends JpaRepository<Media, String> {
    List<Media> findByRevengePlanTitle(String revengePlanTitle);
    Optional<Media> findByUrl(String url);
}
