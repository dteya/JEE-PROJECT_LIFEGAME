package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.VillagersGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Tax;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    @Inject
    BankAccountDAO bankAccountDAO;

    @Inject
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
    @Transactional
    public void deductTax(Tax tax) {
        bankAccountDAO.collectTax(tax.getAmountTax());
        Collection<fr.pantheonsorbonne.ufr27.miage.dto.Villager> villagersInDebt = new ArrayList<fr.pantheonsorbonne.ufr27.miage.dto.Villager>();
        for (Villager villager : bankAccountDAO.getVillagersInDebt()) {
            villagersInDebt.add(new fr.pantheonsorbonne.ufr27.miage.dto.Villager(villager.getId()));
        }
        villagersGateway.sendVillagers(villagersInDebt);
    }
}
