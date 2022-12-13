package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Loan;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Tax;

public interface BankingService {

    void creditBankAccount(Pension pension);

    void debitBankAccount(int amount, int idVillager);

    boolean sufficientBalance(int amount, int idVillager);

    void creditBankAccount(int villagerId, Loan loan);

    void deductTax(Tax tax);
}
