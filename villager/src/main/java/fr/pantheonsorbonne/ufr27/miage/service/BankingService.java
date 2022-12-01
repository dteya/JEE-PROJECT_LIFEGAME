package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Pension;

public interface BankingService {

    void creditBankAccount(Pension pension);
}
