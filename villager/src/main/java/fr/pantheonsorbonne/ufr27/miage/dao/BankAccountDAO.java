package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import java.util.Collection;

public interface BankAccountDAO {

    boolean creditBankAccount(int amount);

    boolean debitBankAccount(int amount, int idVillager);

    int getBalance(int idVillager);

    Collection<Integer> collectTax(int amount);

}
