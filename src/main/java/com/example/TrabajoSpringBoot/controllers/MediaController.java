package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import com.example.TrabajoSpringBoot.services.IServiceMedia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private IServiceMedia serviceMedia;

    @GetMapping("")
    public ResponseEntity<List<MediaDTO>> getMedia() {
        return ResponseEntity.ok(serviceMedia.getMedia());
    }

    @PostMapping("")
    public ResponseEntity<?> addMedia(@RequestBody @Valid MediaDTO mediaDTO,
                                      @RequestParam String revengePlanId) {
        Media media = serviceMedia.addMedia(mediaDTO, revengePlanId);
        return ResponseEntity.ok(media);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeMedia(@PathVariable String id) {
        return serviceMedia.removeMedia(id)
                .map(media -> ResponseEntity.ok().body(media))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getMediaById(@PathVariable String id) {
        return serviceMedia.getMediaById(id)
                .map(media -> ResponseEntity.ok().body(media))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/revenge-plan")
    public ResponseEntity<?> getRevengePlanByMediaId(@PathVariable String id) {
        RevengePlan revengePlan = serviceMedia.getRevengePlanByMediaId(id);
        if (revengePlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(revengePlan);
    }
}