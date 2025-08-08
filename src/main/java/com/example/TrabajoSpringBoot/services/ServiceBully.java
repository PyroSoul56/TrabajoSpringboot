package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.dao.IBullyDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
import com.example.TrabajoSpringBoot.models.RevengePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBully implements IServiceBully {

    @Autowired
    private IBullyDao bullyDao;
    @Autowired
    private IRevengePlanDao revengePlanDao;

    // Implement the methods defined in IServiceBully interface
    @Override
    public List<BullyDTO> getBullies() {
        return bullyDao.findAll().stream().map(
            bully -> {
                return new BullyDTO(
                        bully.getId(),
                        bully.getName(),
                        bully.getNickname(),
                        bully.getHighSchoolRole(),
                        bully.getBullyingReason(),
                        bully.getLevelOfAnnoyance()
                );
            }
        ).toList();
    }

    @Override
    public void addBully(BullyDTO b) {
        Bully bully = new Bully();
        bully.setName(b.name());
        bully.setNickname(b.nickname());
        bully.setHighSchoolRole(b.highSchoolRole());
        Bully search = bullyDao.findByName(bully.getName()).orElse(null);
        if (search != null) {
            throw new NameException("This bully already exists");
        }
        bullyDao.save(bully);
    }

    @Override
    public Optional<Bully> removeBully(String id) {
        return bullyDao.findById(id).map(bully -> {;
            bullyDao.delete(bully);
            return bully;
        });
    }

    @Override
    public Optional<Bully> getBullyById(String id) {
        return bullyDao.findById(id);
    }

    @Override
    public Optional<Bully> getBullyByName(String name) {
        return bullyDao.findByName(name);
    }

    @Override
    public Optional<Clique> getCliqueByBullyId(String id) {
        return bullyDao.findById(id).map(Bully::getClique);
    }

    @Override
    public List<RevengePlan> getRevengePlansByBullyId(String id) {
        return revengePlanDao.findRevengePlansByBullyId(id);
    }

}
