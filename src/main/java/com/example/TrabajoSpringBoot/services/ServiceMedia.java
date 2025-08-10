package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.dao.IMediaDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
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
                media.getId(),
                media.getType(),
                media.getUrl(),
                media.getCaption(),
                media.getRevengePlan() != null ? media.getRevengePlan().getTitle() : null
            )
        ).toList();
    }

    @Override
    public List<MediaDTO> addMedia(List<MediaDTO> mediaDTOs) {
        List<Media> mediaList = mediaDTOs.stream().map(m -> {
            if (m.revengePlanTitle() == null || m.revengePlanTitle().isEmpty()) {
                throw new NameException("Revenge plan title is required in MediaDTO");
            }
            RevengePlan revengePlan = revengePlanDao.findRevengePlanByTitle(m.revengePlanTitle())
                .orElseThrow(() -> new NameException("Revenge plan not found: " + m.revengePlanTitle()));
            Media media = new Media();
            Media search = mediaDao.findByUrl(m.url()).orElse(null);
            if (search != null) {
                throw new NameException("This media already exists: " + m.url());
            }
            media.setType(m.type());
            media.setUrl(m.url());
            media.setCaption(m.caption());
            if (m.id() != null && !m.id().isEmpty()) {
                media.setId(m.id());
            }
            media.setRevengePlan(revengePlan);
            return media;
        }).toList();
        return mediaDao.saveAll(mediaList).stream().map(
            saved -> new MediaDTO(
                saved.getId(),
                saved.getType(),
                saved.getUrl(),
                saved.getCaption(),
                saved.getRevengePlan() != null ? saved.getRevengePlan().getTitle() : null
            )
        ).toList();
    }

    @Override
    public Optional<MediaDTO> removeMedia(String id) {
        return mediaDao.findById(id).map(media -> {
            mediaDao.delete(media);
            return new MediaDTO(
                media.getId(),
                media.getType(),
                media.getUrl(),
                media.getCaption(),
                media.getRevengePlan() != null ? media.getRevengePlan().getTitle() : null
            );
        });
    }

    @Override
    public Optional<MediaDTO> getMediaById(String id) {
        return mediaDao.findById(id).map(media -> new MediaDTO(
            media.getId(),
            media.getType(),
            media.getUrl(),
            media.getCaption(),
            media.getRevengePlan() != null ? media.getRevengePlan().getTitle() : null
        ));
    }

    @Override
    public Optional<RevengePlanDTO> getRevengePlanByMediaId(String id) {
        return mediaDao.findById(id)
            .map(Media::getRevengePlan)
            .filter(rp -> rp != null)
            .map(rp -> new RevengePlanDTO(
                rp.getId(),
                rp.getTitle(),
                rp.getDescription(),
                rp.getDatePlanned(),
                rp.isExecution(),
                rp.getSuccessLevel().name(),
                rp.getRelatedMedia().stream().map(media -> new MediaDTO(
                    media.getId(),
                    media.getType(),
                    media.getUrl(),
                    media.getCaption(),
                    null // No need to include revenge plan title here
                )).toString()
            ));
    }
}
