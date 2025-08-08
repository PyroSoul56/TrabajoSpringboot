package com.example.TrabajoSpringBoot.services;

import com.example.TrabajoSpringBoot.dao.IBullyDao;
import com.example.TrabajoSpringBoot.dao.ICliqueDao;
import com.example.TrabajoSpringBoot.dto.CliqueDTO;
import com.example.TrabajoSpringBoot.models.Bully;
import com.example.TrabajoSpringBoot.models.Clique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                clique.getDescription()
            )
        ).toList(
        );
    }

    @Override
    public Clique addClique(CliqueDTO c) {
        Clique clique = new Clique();
        clique.setName(c.name());
        clique.setDescription(c.description());
        Clique search = cliqueDao.findCliqueByName(clique.getName()).orElse(null);
        if (search != null) {
            throw new IllegalArgumentException("This clique already exists");
        }
        return cliqueDao.save(clique);
    }

    @Override
    public Optional<Clique> removeClique(String id) {
        return cliqueDao.findById(id).map(clique -> {;
            cliqueDao.delete(clique);
            return clique;
        });
    }

    @Override
    public Optional<Clique> getCliqueById(String id) {
        return cliqueDao.findById(id);
    }

    @Override
    public Optional<Clique> getCliqueByName(String name) {
        return cliqueDao.findCliqueByName(name);
    }

    @Override
    public List<Bully> getBulliesByCliqueId(String id) {
        return bullyDao.findByCliqueId(id);
    }
}
