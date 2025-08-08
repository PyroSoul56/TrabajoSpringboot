package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.RevengePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRevengePlanDao extends JpaRepository<RevengePlan, String> {
    List<RevengePlan> findRevengePlansByBullyId(String id);
    Optional<RevengePlan> findRevengePlanByTitle(String name);
    List<RevengePlan> findRevengePlanByExecutionIs(boolean execution);

}
