package fr.pantheonsorbonne.ufr27.miage.dao;

public interface BankAccountDAO {

    boolean creditBankAccount(int amount);

    int getAmountBankAccount(int idVillager);
}
