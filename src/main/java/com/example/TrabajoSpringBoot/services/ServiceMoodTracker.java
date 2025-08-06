package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dao.IMoodTrackerDao;
import com.example.TrabajoSpringBoot.dto.MoodTrackerDTO;
import com.example.TrabajoSpringBoot.models.MoodTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMoodTracker implements  IServiceMoodTracker{

    @Autowired
    private IMoodTrackerDao moodTrackerDao;

    @Override
    public List<MoodTrackerDTO> getMoodTrackers() {
        return moodTrackerDao.findAll().stream().map(
            moodTracker -> new MoodTrackerDTO(
                moodTracker.getMoodLevel(),
                moodTracker.getNote(),
                moodTracker.getDate()
            )
        ).toList();
    }

    @Override
    public MoodTracker addMoodTracker(MoodTrackerDTO m) {
        MoodTracker moodTracker = new MoodTracker();
        moodTracker.setMoodLevel(m.moodLevel());
        moodTracker.setNote(m.note());
        moodTracker.setDate(m.date());
        MoodTracker search = moodTrackerDao.findById(moodTracker.getId()).orElse(null);
        if (search != null) {
            throw new IllegalArgumentException("This mood tracker already exists");
        }
        return moodTrackerDao.save(moodTracker);
    }

    @Override
    public Optional<MoodTracker> removeMoodTracker(String id) {
        return moodTrackerDao.findById(id).map(moodTracker -> {;
            moodTrackerDao.delete(moodTracker);
            return moodTracker;
        });
    }

    @Override
    public Optional<MoodTracker> getMoodTrackerById(String id) {
        return moodTrackerDao.findById(id);
    }

    @Override
    public List<MoodTracker> getMoodTrackerByMoodLevel(int moodLevel) {
        return moodTrackerDao.findByMoodLevel(moodLevel);
    }
}
