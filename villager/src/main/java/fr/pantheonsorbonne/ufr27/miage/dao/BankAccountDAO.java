package fr.pantheonsorbonne.ufr27.miage.dao;

public interface BankAccountDAO {

    boolean creditBankAccount(int amount);

    boolean debitBankAccount(int amount, int idVillager);

    int getBalance(int idVillager);

}
