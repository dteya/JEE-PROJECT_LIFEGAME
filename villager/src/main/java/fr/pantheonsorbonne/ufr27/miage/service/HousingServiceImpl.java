package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.camel.HousingGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class HousingServiceImpl implements HousingService {

    final int priceHouse = 4000;

    @Inject
    BankAccountDAO bankAccountDAO;

    @Inject
    VillagerDAO villagerDAO;

    @Inject
    InventoryDAO inventoryDAO;

    @Inject
    BankingService bankingService;

    @Inject
    HousingGateway housingGateway;

    @Override
    @Transactional
    public Boolean upgradeHouse(int idVillager) {
        int amount = bankAccountDAO.getBalance(idVillager);
        int lvlVillager = villagerDAO.getVillager(idVillager).getLevel();
        int houseUpgrade = priceHouse * (lvlVillager + 1);
        if (amount >= houseUpgrade){
            villagerDAO.updateLvlVillager(idVillager);
            inventoryDAO.resetInventory(idVillager);
            bankingService.debitBankAccount(houseUpgrade, idVillager);
            housingGateway.upgradeHouse(idVillager);
            return true;
        }
        return false;
    }
}
