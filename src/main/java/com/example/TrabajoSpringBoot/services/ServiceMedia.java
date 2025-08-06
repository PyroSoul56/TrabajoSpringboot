package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dao.IMediaDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.models.Media;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMedia implements IServiceMedia{

    @Autowired
    private IMediaDao mediaDao;
    @Autowired
    private IRevengePlanDao revengePlanDao;

    @Override
    public List<MediaDTO> getMedia() {
        return mediaDao.findAll().stream().map(
            media -> new MediaDTO(
                media.getType(),
                media.getUrl(),
                media.getCaption()
            )
        ).toList();
    }

    @Override
    public Media addMedia(MediaDTO m, String revengePlanId) {
        Media media = new Media();
        media.setType(m.type());
        media.setUrl(m.url());
        RevengePlan revengePlan = revengePlanDao.findById(revengePlanId).isPresent()
            ? revengePlanDao.findById(revengePlanId).get()
            : null;;
        media.setRevengePlan(revengePlan);
        Media search = mediaDao.findByUrl(media.getUrl()).orElse(null);
        if (search != null) {
            throw new IllegalArgumentException("This media already exists");
        }
        return mediaDao.save(media);
    }

    @Override
    public Optional<Media> removeMedia(String id) {
        return mediaDao.findById(id).map(media -> {;
            mediaDao.delete(media);
            return media;
        });
    }

    @Override
    public Optional<Media> getMediaById(String id) {
        return mediaDao.findById(id);
    }

    @Override
    public RevengePlan getRevengePlanByMediaId(String id) {
        return null;
    }
}
