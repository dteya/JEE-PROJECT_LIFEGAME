package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.InsufficientFundsException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HousingServiceImpl implements HousingService {

    final int priceHouse = 1000;

    @Inject
    BankAccountDAO bankAccountDAO;

    @Inject
    VillagerDAO villagerDAO;

    @Override
    public Boolean upgradeHouse(int idVillager) throws InsufficientFundsException {
        int amount = bankAccountDAO.getAmountBankAccount(idVillager);
        int lvlVillager = villagerDAO.getLvlVillager(idVillager);
        if (amount >= priceHouse * (lvlVillager + 1)){
            villagerDAO.updateLvlVillager(idVillager);
            return true;
        }
        throw new InsufficientFundsException(idVillager);
    }
}
