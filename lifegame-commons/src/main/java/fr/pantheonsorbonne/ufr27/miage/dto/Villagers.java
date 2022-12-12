package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;
import java.util.Collection;

public class Villagers implements Serializable {

    Collection<Villager> villagers;

    public Villagers(Collection<Villager> villagers) {
        this.villagers = villagers;
    }

    public Collection<Villager> getVillagers() {
        return villagers;
    }

    public void setVillagers(Collection<Villager> villagers) {
        this.villagers = villagers;
    }
}
