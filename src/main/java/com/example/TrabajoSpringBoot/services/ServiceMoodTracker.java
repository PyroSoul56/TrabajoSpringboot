package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
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
                moodTracker.getId(),
                moodTracker.getMoodLevel(),
                moodTracker.getNote(),
                moodTracker.getDate()
            )
        ).toList();
    }

    @Override
    public Optional<MoodTrackerDTO> addMoodTracker(MoodTrackerDTO m) {
        MoodTracker moodTracker = new MoodTracker();
        if (m.id() != null && !m.id().isEmpty()) {
            MoodTracker search = moodTrackerDao.findById(m.id()).orElse(null);
            if (search != null) {
                throw new NameException("This mood tracker already exists");
            }
            moodTracker.setId(m.id());
        }
        if (m.date() == null) {
            moodTracker.setDate(java.time.LocalDate.now());
        } else {
            moodTracker.setDate(m.date());
        }
        moodTracker.setMoodLevel(m.moodLevel());
        moodTracker.setNote(m.note());
        MoodTracker saved = moodTrackerDao.save(moodTracker);
        return Optional.of(new MoodTrackerDTO(
            saved.getId(),
            saved.getMoodLevel(),
            saved.getNote(),
            saved.getDate()
        ));
    }

    @Override
    public Optional<MoodTrackerDTO> removeMoodTracker(String id) {
        return moodTrackerDao.findById(id).map(moodTracker -> {
            moodTrackerDao.delete(moodTracker);
            return new MoodTrackerDTO(
                moodTracker.getId(),
                moodTracker.getMoodLevel(),
                moodTracker.getNote(),
                moodTracker.getDate()
            );
        });
    }

    @Override
    public Optional<MoodTrackerDTO> getMoodTrackerById(String id) {
        return moodTrackerDao.findById(id).map(moodTracker -> new MoodTrackerDTO(
            moodTracker.getId(),
            moodTracker.getMoodLevel(),
            moodTracker.getNote(),
            moodTracker.getDate()
        ));
    }

    @Override
    public List<MoodTrackerDTO> getMoodTrackerByMoodLevel(int moodLevel) {
        return moodTrackerDao.findByMoodLevel(moodLevel).stream().map(
            moodTracker -> new MoodTrackerDTO(
                moodTracker.getId(),
                moodTracker.getMoodLevel(),
                moodTracker.getNote(),
                moodTracker.getDate()
            )
        ).toList();
    }
}
