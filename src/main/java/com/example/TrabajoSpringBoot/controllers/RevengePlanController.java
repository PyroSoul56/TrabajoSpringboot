package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import com.example.TrabajoSpringBoot.services.IServiceRevengePlan;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenge-plans")
public class RevengePlanController {

    @Autowired
    private IServiceRevengePlan serviceRevengePlan;

    @GetMapping("")
    public ResponseEntity<List<RevengePlanDTO>> getRevengePlans() {
        return ResponseEntity.ok(serviceRevengePlan.getRevengePlan());
    }

    @PostMapping("")
    public ResponseEntity<?> addRevengePlan(@RequestBody @Valid RevengePlanDTO revengePlanDTO) {
        RevengePlan revengePlan = serviceRevengePlan.addRevengePlan(revengePlanDTO);
        return ResponseEntity.ok(revengePlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeRevengePlan(@PathVariable String id) {
        return serviceRevengePlan.removeRevengePlan(id)
                .map(plan -> ResponseEntity.ok().body(plan))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRevengePlanById(@PathVariable String id) {
        return serviceRevengePlan.getRevengePlanById(id)
                .map(plan -> ResponseEntity.ok().body(plan))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getRevengePlanByTitle(@PathVariable String title) {
        return serviceRevengePlan.getRevengePlanByTitle(title)
                .map(plan -> ResponseEntity.ok().body(plan))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/executed/{executed}")
    public ResponseEntity<List<RevengePlan>> findRevengePlanByExecutionIs(@PathVariable boolean executed) {
        List<RevengePlan> plans = serviceRevengePlan.findRevengePlanByExecutionIs(executed);
        if (plans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}/media")
    public ResponseEntity<?> getMediaByRevengePlanId(@PathVariable String id) {
        List<Media> media = serviceRevengePlan.getMediaByRevengePlanId(id);
        if (media.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(media);
    }
}