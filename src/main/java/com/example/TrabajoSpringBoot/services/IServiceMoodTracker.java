package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.MoodTrackerDTO;
import com.example.TrabajoSpringBoot.models.MoodTracker;

import java.util.List;
import java.util.Optional;

public interface IServiceMoodTracker {
    public List<MoodTrackerDTO> getMoodTrackers();
    public MoodTracker addMoodTracker(MoodTrackerDTO m);
    public Optional<MoodTracker> removeMoodTracker(int id);
    public Optional<MoodTracker> getMoodTrackerById(int id);
}
