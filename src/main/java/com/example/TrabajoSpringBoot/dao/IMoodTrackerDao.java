package com.example.TrabajoSpringBoot.dao;

import com.example.TrabajoSpringBoot.models.MoodTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMoodTrackerDao extends JpaRepository<MoodTracker, String> {
    List<MoodTracker> findByMoodLevel(int moodLevel);
}
