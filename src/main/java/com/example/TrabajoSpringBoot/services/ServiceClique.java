package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.Exceptions.NameException;
import com.example.TrabajoSpringBoot.dao.IBullyDao;
import com.example.TrabajoSpringBoot.dao.ICliqueDao;
import com.example.TrabajoSpringBoot.dto.BullyDTO;
import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceClique implements IServiceClique{

    @Autowired
    private ICliqueDao cliqueDao;
    @Autowired
    private IBullyDao bullyDao;

    @Override
    public List<CliqueDTO> getCliques() {
        return cliqueDao.findAll().stream().map(
            clique -> new CliqueDTO(
                clique.getId(),
                clique.getName(),
                clique.getDescription(),
                clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
            )
        ).toList(
        );
    }

    @Override
    public List<CliqueDTO> addCliques(List<CliqueDTO> cliques) {
        //check for duplicates
        for (CliqueDTO cliqueDTO : cliques) {
            if (cliqueDao.findByName(cliqueDTO.name()).isPresent()) {
                throw new NameException("The clique "
                        +cliqueDTO.name()+" already exists");
            }
        }
        List<Clique> cliqueList = cliques.stream().map(c -> {
            Clique clique = new Clique();
            clique.setName(c.name());
            clique.setDescription(c.description());
            //if id is set, it will be used, otherwise a new one will be generated
            if (c.id() != null && !c.id().isEmpty()) {
                clique.setId(c.id());
            }
            return clique;
        }).toList();
        return cliqueDao.saveAll(cliqueList).stream().map(
            clique -> new CliqueDTO(
                clique.getId(),
                clique.getName(),
                clique.getDescription(),
                clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
            )
        ).toList();
    }

    @Override
    public Optional<CliqueDTO> removeClique(String id) {
        return cliqueDao.findById(id).map(clique -> {
            cliqueDao.delete(clique);
            return new CliqueDTO(
                clique.getId(),
                clique.getName(),
                clique.getDescription(),
                clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
            );
        });
    }

    @Override
    public Optional<CliqueDTO> getCliqueById(String id) {
        return cliqueDao.findById(id).map(clique -> new CliqueDTO(
            clique.getId(),
            clique.getName(),
            clique.getDescription(),
            clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
        ));
    }

    @Override
    public Optional<CliqueDTO> getCliqueByName(String name) {
        return cliqueDao.findByName(name).map(clique -> new CliqueDTO(
            clique.getId(),
            clique.getName(),
            clique.getDescription(),
            clique.getBullies() != null ? clique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
        ));
    }

    @Override
    public List<BullyDTO> getBulliesByCliqueName(String name) {
        return bullyDao.findByCliqueName(name).stream().map(
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
    public Optional<CliqueDTO> setBullies(String cliqueName, List<String> bullyNames) {
        Optional<Clique> optionalClique = cliqueDao.findByName(cliqueName);
        if (optionalClique.isPresent()) {
            Clique clique = optionalClique.get();
            Set<Bully> bullies = bullyNames.stream()
                .map(bullyName -> bullyDao.findByName(bullyName)
                    .orElseThrow(() -> new NameException("Bully by name " + bullyName + " not found")))
                .collect(Collectors.toSet());
            if (bullies.isEmpty()) {
                throw new NameException("No valid bullies found for the clique");
            }
            for (Bully bully : bullies) {
                if (bully.getClique() != null) {
                    throw new NameException("Bully " + bully.getName() + " is already in another clique: " + bully.getClique().getName());
                }
            }
            clique.setBullies(bullies.stream().map(bully ->  bullyDao.save(bully)).collect(Collectors.toSet()));
            bullies.forEach(bully -> {
                bully.setClique(clique);
                bullyDao.save(bully);
            });
            Clique savedClique = cliqueDao.save(clique);
            return Optional.of(new CliqueDTO(
                savedClique.getId(),
                savedClique.getName(),
                savedClique.getDescription(),
                savedClique.getBullies() != null ? savedClique.getBullies().stream().map(Bully::getName).toList() : java.util.Collections.emptyList()
            ));
        }
        throw new NameException("Clique by name " + cliqueName + " not found");
    }
}
