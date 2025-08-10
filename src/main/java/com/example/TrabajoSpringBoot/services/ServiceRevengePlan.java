package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.controllers.SuccessLevelEnum;
import com.example.TrabajoSpringBoot.dao.IBullyDao;
import com.example.TrabajoSpringBoot.dao.IMediaDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.MediaDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceRevengePlan implements IServiceRevengePlan{

    @Autowired
    private IRevengePlanDao revengePlanDao;
    @Autowired
    private IMediaDao mediaDao;
    @Autowired
    private IBullyDao bullyDao;

    @Override
    public List<RevengePlanDTO> getRevengePlan() {
        return revengePlanDao.findAll().stream().map(
            revengePlan -> new RevengePlanDTO(
                revengePlan.getId(),
                revengePlan.getTitle(),
                revengePlan.getDescription(),
                revengePlan.getDatePlanned(),
                revengePlan.isExecution(),
                revengePlan.getSuccessLevel() != null ? revengePlan.getSuccessLevel().name() : null,
                revengePlan.getBully() != null ? revengePlan.getBully().getName() : null
            )
        ).toList();
    }

    @Override
    public List<RevengePlanDTO> addRevengePlans(List<RevengePlanDTO> r) {

        for (RevengePlanDTO revengePlanDTO : r) {
            if (revengePlanDao.findRevengePlanByTitle(revengePlanDTO.title()).isPresent()) {
                throw new NameException("The revenge plan "
                        + revengePlanDTO.title() + " already exists");
            }
            if (revengePlanDTO.successLevel() != null && !revengePlanDTO.successLevel().isEmpty()) {
                try {
                    SuccessLevelEnum.valueOf(revengePlanDTO.successLevel());
                } catch (IllegalArgumentException e) {
                    throw new NameException("Invalid success level: " + revengePlanDTO.successLevel());
                }
            }
        }

            List<RevengePlan> revengePlanList = r.stream().map(revengePlanDTO -> {
                RevengePlan revengePlan = new RevengePlan();
                // If id is set, it will be used, otherwise a new one will be generated
                if (revengePlanDTO.id() != null && !revengePlanDTO.id().isEmpty()) {
                    revengePlan.setId(revengePlanDTO.id());
                }
                revengePlan.setTitle(revengePlanDTO.title());
                revengePlan.setDescription(revengePlanDTO.description());
                revengePlan.setDatePlanned(revengePlanDTO.datePlanned());
                revengePlan.setExecution(revengePlanDTO.execution());
                revengePlan.setSuccessLevel(revengePlanDTO.successLevel() != null && !revengePlanDTO.successLevel().isEmpty() ?
                        SuccessLevelEnum.valueOf(revengePlanDTO.successLevel()) : null);
                revengePlan.setBully(
                        revengePlanDTO.bullyName() != null ? bullyDao.findByName(revengePlanDTO.bullyName()).orElseThrow(() ->
                                new NameException("Bully not found: " + revengePlanDTO.bullyName())) : null
                );
                return revengePlan;
            }).toList();
            return revengePlanDao.saveAll(revengePlanList).stream().map(
                    rp -> new RevengePlanDTO(
                            rp.getId(),
                            rp.getTitle(),
                            rp.getDescription(),
                            rp.getDatePlanned(),
                            rp.isExecution(),
                            rp.getSuccessLevel() != null ? rp.getSuccessLevel().name() : null,
                            rp.getBully() != null ? rp.getBully().getName() : null
                    )
            ).toList();
    }

    @Override
    public Optional<RevengePlanDTO> removeRevengePlan(String id) {
        return revengePlanDao.findById(id).map(revengePlan -> {
            revengePlanDao.delete(revengePlan);
            return new RevengePlanDTO(
                revengePlan.getId(),
                revengePlan.getTitle(),
                revengePlan.getDescription(),
                revengePlan.getDatePlanned(),
                revengePlan.isExecution(),
                revengePlan.getSuccessLevel() != null ? revengePlan.getSuccessLevel().name() : null,
                revengePlan.getBully() != null ? revengePlan.getBully().getName() : null
            );
        });
    }

    @Override
    public Optional<RevengePlanDTO> getRevengePlanById(String id) {
        return revengePlanDao.findById(id).map(rp -> new RevengePlanDTO(
            rp.getId(),
            rp.getTitle(),
            rp.getDescription(),
            rp.getDatePlanned(),
            rp.isExecution(),
            rp.getSuccessLevel() != null ? rp.getSuccessLevel().name() : null,
            rp.getBully() != null ? rp.getBully().getName() : null
        ));
    }

    @Override
    public Optional<RevengePlanDTO> getRevengePlanByTitle(String name) {
        return revengePlanDao.findRevengePlanByTitle(name).map(rp -> new RevengePlanDTO(
            rp.getId(),
            rp.getTitle(),
            rp.getDescription(),
            rp.getDatePlanned(),
            rp.isExecution(),
            rp.getSuccessLevel() != null ? rp.getSuccessLevel().name() : null,
            rp.getBully() != null ? rp.getBully().getName() : null
        ));
    }

    @Override
    public List<RevengePlanDTO> findRevengePlanByExecutionIs(boolean execution) {
        return revengePlanDao.findRevengePlanByExecutionIs(execution).stream().map(
            rp -> new RevengePlanDTO(
                rp.getId(),
                rp.getTitle(),
                rp.getDescription(),
                rp.getDatePlanned(),
                rp.isExecution(),
                rp.getSuccessLevel() != null ? rp.getSuccessLevel().name() : null,
                rp.getBully() != null ? rp.getBully().getName() : null
            )
        ).toList();
    }

    @Override
    public List<MediaDTO> getMediaByRevengePlanTitle(String title) {
        return mediaDao.findByRevengePlanTitle(title).stream().map(
            media -> new MediaDTO(
                media.getId(),
                media.getType(),
                media.getUrl(),
                media.getCaption(),
                media.getRevengePlan() != null ? media.getRevengePlan().getTitle() : null
            )
        ).toList();
    }
}
