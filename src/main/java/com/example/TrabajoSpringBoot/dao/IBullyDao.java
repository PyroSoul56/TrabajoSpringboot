package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.Bully;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBullyDao  extends JpaRepository <Bully, String> {

    

}
