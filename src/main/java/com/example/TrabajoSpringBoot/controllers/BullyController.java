package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.BullyDTO;
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

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public ResponseEntity<List<BullyDTO>> getBullies() {
        return ResponseEntity.ok(serviceBully.getBullies());
    }

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<List<BullyDTO>> addBullies(@RequestBody @Valid List<BullyDTO> bullies) {
        List<BullyDTO> addedBullies = serviceBully.addBullies(bullies);
        return ResponseEntity.ok(addedBullies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBully(@PathVariable String id) {
        return serviceBully.removeBully(id)
                .map(bully -> ResponseEntity.ok().body(bully))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBullyByName(@PathVariable String name) {
        return serviceBully.getBullyByName(name)
                .map(bully -> ResponseEntity.ok().body(bully))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/clique")
    public ResponseEntity<?> getCliqueByBullyName(@PathVariable String name) {
        return serviceBully.getCliqueByBullyName(name)
                .map(clique -> ResponseEntity.ok().body(clique))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/revenge-plan")
    public ResponseEntity<?> getRevengePlansByBullyName(@PathVariable String name) {
        var revengePlans = serviceBully.getRevengePlansByBullyName(name);
        if (revengePlans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(revengePlans);
    }

}
