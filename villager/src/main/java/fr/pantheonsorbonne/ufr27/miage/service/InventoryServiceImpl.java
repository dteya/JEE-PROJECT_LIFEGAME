package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Inventory;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InventoryServiceImpl implements InventoryService {

    @Inject
    VillagerDAO villagerDAO;

    @Inject
    InventoryDAO inventoryDAO;


    @Override
    public boolean sufficientSpace(int idVillager) {
        Villager villager = villagerDAO.getVillager(idVillager);
        Inventory inventory = inventoryDAO.getInventory(idVillager);
        if (inventory.getInventoryCapacity() + 1 <= villager.getLevel() * 5) {
            return true;
        }

        return false;
    }
}
