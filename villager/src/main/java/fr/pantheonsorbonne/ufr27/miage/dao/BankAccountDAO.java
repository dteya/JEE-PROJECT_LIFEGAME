package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import java.util.Collection;

public interface BankAccountDAO {

    boolean debitBankAccount(int idVillager, int amount);

    int getBalance(int idVillager);

    boolean creditBankAccount(int villagerId, int amount);
}
