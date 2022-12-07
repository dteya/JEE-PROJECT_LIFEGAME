package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Inventory;

public interface InventoryDAO {

    void addToInventory(int idVillager);

    void resetInventory(int idVillager);

    Inventory getInventory(int idVillager);



}
