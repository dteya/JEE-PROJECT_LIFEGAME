package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

public interface VillagerDAO {

    boolean updateLvlVillager(int idVillager);

    Villager getVillager(int idVillager);

}
