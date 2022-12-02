package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.HousingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.InsufficientFundsException;

public class HousingServiceImpl implements HousingService {

    int priceHouse = 1000;
    int CapacityHouse = 5;

    HousingDAO housingDAO;
    VillagerDAO villagerDAO;

    @Override
    public Boolean upgradeHouse(int idVillager, int lvlVillager) throws InsufficientFundsException {
        int amount = housingDAO.getAmountBankAccount(idVillager);
        lvlVillager = villagerDAO.getLvlVillager(lvlVillager);
        if (amount >= priceHouse * (lvlVillager + 1)){
            villagerDAO.updateLvlVillager(idVillager);
            CapacityHouse = CapacityHouse * (lvlVillager +1);
            return true;
        }
        throw new InsufficientFundsException(idVillager);
    }
}
