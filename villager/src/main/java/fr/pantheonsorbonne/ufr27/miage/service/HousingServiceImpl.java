package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.HousingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;

public class HousingServiceImpl implements HousingService {

    int priceHouse = 1000;
    int CapacityHouse = 5;

    HousingDAO housingDAO;
    VillagerDAO villagerDAO;

    @Override
    public Boolean upgradeHouse(int IdVillager, int lvlVillager){
        int amount = housingDAO.getMountantBankAccount(IdVillager);
        lvlVillager = villagerDAO.getLvlVillager(lvlVillager);
        if (amount >= priceHouse * (lvlVillager + 1)){
            villagerDAO.updateLvlVillager(IdVillager);
            CapacityHouse = CapacityHouse * (lvlVillager +1);
        }
    return false;
    }
}
