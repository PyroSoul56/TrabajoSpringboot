package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import com.example.TrabajoSpringBoot.services.IServiceBully;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bullies")
public class BullyController {

    @Autowired
    private IServiceBully serviceBully;

    @GetMapping("/")
    public ResponseEntity<List<BullyDTO>> getBullies() {
        return ResponseEntity.ok(serviceBully.getBullies());
    }

    @PostMapping("/")
    public ResponseEntity<?> addBully(@RequestBody @Valid BullyDTO bullyDTO) {
        System.out.println(bullyDTO);
        serviceBully.addBully(bullyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBully(@PathVariable String id) {
        return serviceBully.removeBully(id)
                .map(bully -> ResponseEntity.ok().body(bully))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<BullyDTO>> getAllBullies() {
        List<BullyDTO> bullies = serviceBully.getBullies();
        return ResponseEntity.ok(bullies);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBulliesById(@PathVariable String id) {
        return serviceBully.getBullyById(id)
                .map(bully -> ResponseEntity.ok().body(bully))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getBullyByName(@PathVariable String name) {
        return serviceBully.getBullyByName(name)
                .map(bully -> ResponseEntity.ok().body(bully))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/clique")
    public ResponseEntity<?> getCliqueByBullyId(@PathVariable String id) {
        return serviceBully.getCliqueByBullyId(id)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/revenge-plans")
    public ResponseEntity<?> getRevengePlansByBullyId(@PathVariable String id) {
        List<RevengePlan> revengePlans = serviceBully.getRevengePlansByBullyId(id);
        if (revengePlans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(revengePlans);
    }

}
