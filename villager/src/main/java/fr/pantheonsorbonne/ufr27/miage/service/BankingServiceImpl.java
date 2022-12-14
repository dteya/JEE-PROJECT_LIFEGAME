package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.VillagersGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Loan;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Tax;
import fr.pantheonsorbonne.ufr27.miage.dto.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    private static final Logger LOGGER = Logger.getLogger("logger");

    @Inject
    BankAccountDAO bankAccountDAO;

    @Inject
    VillagersGateway villagersGateway;

    @Override
    public boolean sufficientBalance(int amount, int idVillager) {
        if (amount <= bankAccountDAO.getBalance(idVillager)) {
            LOGGER.info("Villager " + idVillager + " has a sufficient balance");
            return true;
        }
        LOGGER.info("Villager " + idVillager + " hasn't a sufficient balance");
        return false;
    }

    @Override
    public void creditBankAccount(int villagerId, Loan loan) {
        villagersGateway.sendVillager(
                new Villager(
                        villagerId
                ),
                bankAccountDAO.creditBankAccount(villagerId, loan.getLoanAmount())
        );
        LOGGER.info("Villager " + villagerId + "has been credited of " + loan.getLoanAmount());
    }

    @Override
    public void creditPension(int villagerId, Pension pension) {
        villagersGateway.sendVillager(
                new Villager(
                        villagerId
                ),
                bankAccountDAO.creditBankAccount(villagerId, pension.getAmount())

        );
        LOGGER.info("Villager " + villagerId + "has received a pension of " + pension.getAmount());

    }

    @Override
    public void debitBankAccount(int amount, int idVillager) {
        bankAccountDAO.debitBankAccount(idVillager, amount);
    }

    @Override
    @Transactional
    public void deductTax(Tax tax, int idVillager) {
        villagersGateway.sendVillager(
                new Villager(
                        idVillager
                ),
                bankAccountDAO.debitBankAccount(idVillager, tax.getAmountTax())
        );
        LOGGER.info("Villager " + idVillager + "has been debited of " + tax.getAmountTax());
    }
}
