package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.VillagersGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Tax;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    @Inject
    BankAccountDAO bankAccountDAO;

    VillagersGateway villagersGateway;

    @Override
    public void creditBankAccount(Pension pension) {
        bankAccountDAO.creditBankAccount(pension.getAmount());
    }

    @Override
    public void debitBankAccount(int amount, int idVillager) {
        bankAccountDAO.debitBankAccount(amount, idVillager);
    }

    @Override
    public boolean sufficientBalance(int amount, int idVillager) {
        if (amount <= bankAccountDAO.getBalance(idVillager)) {
            return true;
        }
        return false;
    }

    @Override
    public void deductTax(Tax tax) {

        villagersGateway.sendVillagers(bankAccountDAO.collectTax(tax.getAmountTax()));
    }
}
