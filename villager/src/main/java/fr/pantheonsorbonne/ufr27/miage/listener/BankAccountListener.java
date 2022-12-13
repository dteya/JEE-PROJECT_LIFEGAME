package fr.pantheonsorbonne.ufr27.miage.listener;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;

import javax.persistence.*;

public class BankAccountListener {
    @PreUpdate
    public void preUpdate(BankAccount b) {
        if(b.getBalance() >= 0) {
            b.getOwner().setBannedStatus(false);
        }
        if(b.getBalance() < 0) {
            b.getOwner().setBannedStatus(true);
        }
    }
}
