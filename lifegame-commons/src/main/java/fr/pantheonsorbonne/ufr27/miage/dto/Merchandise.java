package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;
import java.util.Collection;

public class Merchandise implements Serializable {

    Collection<Product> merchandise;

    public Merchandise(Collection<Product> merchandise) {
        this.merchandise = merchandise;
    }


    public Collection<Product> getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Collection<Product> merchandise) {
        this.merchandise = merchandise;
    }
}
