package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.BankingGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class BankingServiceImpl implements BankingService{

    @Inject
    BankingGateway bankingGateway;

    private static final Logger LOGGER = Logger.getLogger("logger");

    public void emitPension(int amount) {
        bankingGateway.emitPension(amount);
        LOGGER.info("A pension of " + amount + " has been emitted");
    }

    @Override
    public void collectTax() {
        bankingGateway.collectTax();
        LOGGER.info("Tax has been collected");
    }
}
