package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.InsufficientFundsException;
import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;

public interface HousingService {

    Boolean upgradeHouse(int idVillager) throws InsufficientFundsException;
}
