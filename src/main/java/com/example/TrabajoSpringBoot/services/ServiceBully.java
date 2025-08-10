package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.dao.IBullyDao;
import com.example.TrabajoSpringBoot.dao.IRevengePlanDao;
import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.dto.RevengePlanDTO;
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
            bully -> new BullyDTO(
                bully.getId(),
                bully.getName(),
                bully.getNickname(),
                bully.getHighSchoolRole(),
                bully.getBullyingReason(),
                bully.getLevelOfAnnoyance(),
                bully.getClique() != null ? bully.getClique().getName() : null
            )
        ).toList();
    }

    @Override
    public List<BullyDTO> addBullies(List<BullyDTO> bullies) {
        //Check for duplicates
        for (BullyDTO bullyDTO : bullies) {
            if (bullyDao.findByName(bullyDTO.name()).isPresent()) {
                throw new NameException("The bully "
                        +bullyDTO.name()+" already exists");
            }
        }
        List<Bully> bullyList = bullies.stream().map(b -> {
            Bully bully = new Bully();
            bully.setName(b.name());
            bully.setNickname(b.nickname());
            bully.setHighSchoolRole(b.highSchoolRole());
            bully.setBullyingReason(b.bullyingReason());
            bully.setLevelOfAnnoyance(b.levelOfAnnoyance());
            // If id is set, it will be used, otherwise a new one will be generated
            if (b.id() != null && !b.id().isEmpty()) {
                bully.setId(b.id());
            }
            return bully;
        }).toList();
        return bullyDao.saveAll(bullyList).stream().map(
            bully -> new BullyDTO(
                bully.getId(),
                bully.getName(),
                bully.getNickname(),
                bully.getHighSchoolRole(),
                bully.getBullyingReason(),
                bully.getLevelOfAnnoyance(),
                bully.getClique() != null ? bully.getClique().getName() : null
            )
        ).toList();
    }

    @Override
    public Optional<BullyDTO> removeBully(String id) {
        return bullyDao.findById(id).map(bully -> {
            bullyDao.delete(bully);
            return new BullyDTO(
                bully.getId(),
                bully.getName(),
                bully.getNickname(),
                bully.getHighSchoolRole(),
                bully.getBullyingReason(),
                bully.getLevelOfAnnoyance(),
                bully.getClique() != null ? bully.getClique().getName() : null
            );
        });
    }

    @Override
    public Optional<BullyDTO> getBullyById(String id) {
        return bullyDao.findById(id).map(bully -> new BullyDTO(
            bully.getId(),
            bully.getName(),
            bully.getNickname(),
            bully.getHighSchoolRole(),
            bully.getBullyingReason(),
            bully.getLevelOfAnnoyance(),
            bully.getClique() != null ? bully.getClique().getName() : null
        ));
    }

    @Override
    public Optional<BullyDTO> getBullyByName(String name) {
        return bullyDao.findByName(name).map(bully -> new BullyDTO(
            bully.getId(),
            bully.getName(),
            bully.getNickname(),
            bully.getHighSchoolRole(),
            bully.getBullyingReason(),
            bully.getLevelOfAnnoyance(),
            bully.getClique() != null ? bully.getClique().getName() : null
        ));
    }

    @Override
    public Optional<CliqueDTO> getCliqueByBullyName(String name) {
        return bullyDao.findByName(name)
            .map(Bully::getClique)
            .map(clique -> new CliqueDTO(
                clique.getId(),
                clique.getName(),
                clique.getDescription(),
                clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
            ));
    }

    @Override
    public List<RevengePlanDTO> getRevengePlansByBullyName(String name) {
        return revengePlanDao.findRevengePlansByBullyName(name).stream().map(
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

}
