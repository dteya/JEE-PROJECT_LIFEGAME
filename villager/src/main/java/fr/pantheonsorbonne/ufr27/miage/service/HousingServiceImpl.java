package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.camel.HousingGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class HousingServiceImpl implements HousingService {

    private static final Logger LOGGER = Logger.getLogger("logger");

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
            LOGGER.info("Villager " + idVillager + " has upgrade his house");
            return true;
        }
        LOGGER.info("Villager " + idVillager + " doesn't have enough money to upgrade his house");
        return false;
    }
}
