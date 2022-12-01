package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    @Inject
    BankAccountDAO bankAccountDAO;

    @Override
    public void creditBankAccount(int pension) {
        bankAccountDAO.creditBankAccount(pension);
    }
}
