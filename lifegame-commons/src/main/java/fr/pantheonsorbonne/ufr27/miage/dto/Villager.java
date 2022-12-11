package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Villager implements Serializable {

    int id;
    public Villager(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
