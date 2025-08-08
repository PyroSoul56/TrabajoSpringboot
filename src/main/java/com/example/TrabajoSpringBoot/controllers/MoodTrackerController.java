package com.example.TrabajoSpringBoot.controllers;

import com.example.TrabajoSpringBoot.dto.MoodTrackerDTO;
import com.example.TrabajoSpringBoot.models.MoodTracker;
import com.example.TrabajoSpringBoot.services.IServiceMoodTracker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood-trackers")
public class MoodTrackerController {

    @Autowired
    private IServiceMoodTracker serviceMoodTracker;

    @GetMapping("")
    public ResponseEntity<List<MoodTrackerDTO>> getMoodTrackers() {
        return ResponseEntity.ok(serviceMoodTracker.getMoodTrackers());
    }

    @PostMapping("")
    public ResponseEntity<?> addMoodTracker(@RequestBody @Valid MoodTrackerDTO moodTrackerDTO) {
        MoodTracker moodTracker = serviceMoodTracker.addMoodTracker(moodTrackerDTO);
        return ResponseEntity.ok(moodTracker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeMoodTracker(@PathVariable String id) {
        return serviceMoodTracker.removeMoodTracker(id)
                .map(mt -> ResponseEntity.ok().body(mt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getMoodTrackerById(@PathVariable String id) {
        return serviceMoodTracker.getMoodTrackerById(id)
                .map(mt -> ResponseEntity.ok().body(mt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mood-level/{moodLevel}")
    public ResponseEntity<List<MoodTracker>> getMoodTrackerByMoodLevel(@PathVariable int moodLevel) {
        List<MoodTracker> moodTrackers = serviceMoodTracker.getMoodTrackerByMoodLevel(moodLevel);
        if (moodTrackers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moodTrackers);
    }
}