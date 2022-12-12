package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.BankingGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BankingServiceImpl implements BankingService{

    @Inject
    BankingGateway bankingGateway;

    public void emitPension(int amount) {
        bankingGateway.emitPension(amount);
    }

    @Override
    public void collectTax() { bankingGateway.collectTax();
    }
}
