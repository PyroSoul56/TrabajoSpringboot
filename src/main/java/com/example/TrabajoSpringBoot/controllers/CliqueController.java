package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.services.IServiceClique;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliques")
public class CliqueController {

    @Autowired
    private IServiceClique serviceClique;

    @GetMapping("")
    public ResponseEntity<List<CliqueDTO>> getCliques() {
        return ResponseEntity.ok(serviceClique.getCliques());
    }


    @PostMapping("")
    public ResponseEntity<List<CliqueDTO>> addCliques(@RequestBody @Valid List<CliqueDTO> cliqueDTOs) {
        List<CliqueDTO> cliques = serviceClique.addCliques(cliqueDTOs);
        return ResponseEntity.ok(cliques);
    }


    @PostMapping("/{cliqueName}")
    public ResponseEntity<?> setBullies(@PathVariable String cliqueName, @RequestBody List<String> bulliesId) {
        var clique = serviceClique.setBullies(cliqueName, bulliesId);
        return clique.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeClique(@PathVariable String id) {
        return serviceClique.removeClique(id)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCliqueByName(@PathVariable String name) {
        return serviceClique.getCliqueByName(name)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/bullies")
    public ResponseEntity<?> getBulliesByCliqueName(@PathVariable String name) {
        var bullies = serviceClique.getBulliesByCliqueName(name);
        if (bullies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bullies);
    }
}