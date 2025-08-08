package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
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
    public ResponseEntity<?> addClique(@RequestBody @Valid CliqueDTO cliqueDTO) {
        Clique clique = serviceClique.addClique(cliqueDTO);
        return ResponseEntity.ok(clique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeClique(@PathVariable String id) {
        return serviceClique.removeClique(id)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCliqueById(@PathVariable String id) {
        return serviceClique.getCliqueById(id)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCliqueByName(@PathVariable String name) {
        return serviceClique.getCliqueByName(name)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/bullies")
    public ResponseEntity<?> getBulliesByCliqueId(@PathVariable String id) {
        List<Bully> bullies = serviceClique.getBulliesByCliqueId(id);
        if (bullies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bullies);
    }
}