package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.transaction.Transactional;
import java.util.Collection;

public interface VillagerService {

    Collection<Villager> listVillager();

    @Transactional
    Boolean levelUpVillager(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager);
}
