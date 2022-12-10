package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;
import java.util.Collection;

public class Villagers implements Serializable {

    Collection<Integer> villagers;

    public Villagers(Collection<Integer> villagers) {
        this.villagers = villagers;
    }

    public Collection<Integer> getVillagers() {
        return villagers;
    }

    public void setVillagers(Collection<Integer> villagers) {
        this.villagers = villagers;
    }
}
