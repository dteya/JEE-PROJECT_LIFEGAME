package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Pension implements Serializable {

    int amount;

    public Pension(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
