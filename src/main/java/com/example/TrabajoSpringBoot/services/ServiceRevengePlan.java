package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.controllers.SuccessLevelEnum;
import com.example.TrabajoSpringBoot.dao.IMediaDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
import com.example.TrabajoSpringBoot.models.Media;
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

    @Override
    public List<RevengePlanDTO> getRevengePlan() {
        return revengePlanDao.findAll().stream().map(
            revengePlan -> new RevengePlanDTO(
                revengePlan.getTitle(),
                revengePlan.getDescription(),
                revengePlan.getDatePlanned(),
                revengePlan.isExecuted(),
                revengePlan.getSuccessLevel().name()
            )
        ).toList(
        );
    }

    @Override
    public RevengePlan addRevengePlan(RevengePlanDTO r) {
        RevengePlan revengePlan = new RevengePlan();
         revengePlan.setTitle(r.title());
         revengePlan.setDescription(r.description());
         revengePlan.setDatePlanned(r.datePlanned());
         revengePlan.setExecuted(r.isExecuted());
         revengePlan.setSuccessLevel(SuccessLevelEnum.valueOf(r.successLevel()));
        RevengePlan search = revengePlanDao.findById(revengePlan.getId()).orElse(null);
        if (search != null) {
            throw new NameException("This bully already exists");
        }
        return revengePlanDao.save(revengePlan);
    }

    @Override
    public Optional<RevengePlan> removeRevengePlan(String id) {
        return revengePlanDao.findById(id).map(revengePlan -> {;
            revengePlanDao.delete(revengePlan);
            return revengePlan;
        });
    }

    @Override
    public Optional<RevengePlan> getRevengePlanById(String id) {
        return revengePlanDao.findById(id);
    }

    @Override
    public Optional<RevengePlan> getRevengePlanByTitle(String name) {
        return revengePlanDao.findRevengePlanByTitle(name);
    }

    @Override
    public List<RevengePlan> getExecutedRevengePlansByExecution(boolean executed) {
        return revengePlanDao.findRevengePlansByExecuted(executed);
    }

    @Override
    public List<Media> getMediaByRevengePlanId(String id) {
        return mediaDao.findByRevengePlanId(id);
    }
}
