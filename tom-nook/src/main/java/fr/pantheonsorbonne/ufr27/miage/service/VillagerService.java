package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import java.util.Collection;

public interface VillagerService {

    Collection<Villager> listVillager();
    Boolean upgradeVillagerLvl(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager);
}
