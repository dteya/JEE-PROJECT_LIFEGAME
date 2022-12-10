package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HousingServiceImpl implements HousingService {

    final int priceHouse = 4000;

    @Inject
    BankAccountDAO bankAccountDAO;

    @Inject
    VillagerDAO villagerDAO;

    @Inject
    InventoryDAO inventoryDAO;

    @Override
    public Boolean upgradeHouse(int idVillager) {
        int amount = bankAccountDAO.getBalance(idVillager);
        int lvlVillager = villagerDAO.getVillager(idVillager).getLevel();
        if (amount >= priceHouse * (lvlVillager + 1)){
            villagerDAO.updateLvlVillager(idVillager);
            inventoryDAO.resetInventory(idVillager);
            return true;
        }
        return false;
    }
}
