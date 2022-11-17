package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.BankingGateway;

import javax.inject.Inject;

public class BankingServiceImpl implements BankingService{

    @Inject
    BankingGateway bankingGateway;


    public void emitPension(int amount) {
        bankingGateway.emitPension(amount);
    }
}
