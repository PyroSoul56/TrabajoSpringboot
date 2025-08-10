package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dto.MoodTrackerDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceMoodTracker {
    public List<MoodTrackerDTO> getMoodTrackers();
    public Optional<MoodTrackerDTO> addMoodTracker(MoodTrackerDTO m);
    public Optional<MoodTrackerDTO> removeMoodTracker(String id);
    public Optional<MoodTrackerDTO> getMoodTrackerById(String id);
    public List<MoodTrackerDTO> getMoodTrackerByMoodLevel(int moodLevel);
}
