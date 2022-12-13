package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import java.util.Collection;

public interface VillagerDAO {
    Collection<Villager> listAllVillager();

    void levelUpVillager(int idVillager);

    void banVillager(int idVillager);

    Villager getVillager(int villagerId);
}
