package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    @Inject
    BankAccountDAO bankAccountDAO;

    @Override
    public void creditBankAccount(Pension pension) {
        bankAccountDAO.creditBankAccount(pension.getAmount());
    }
}
