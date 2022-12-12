package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.service.LoaningService;
import fr.pantheonsorbonne.ufr27.miage.service.VillagerService;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    VillagerService villagerService;

    @Inject
    LoaningService loaningService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);
        from("jms:queue:upgrade-level")
                .bean(villagerService, "levelUpVillager(${body})");

        from("jms:queue:villagersInDebt")
                .bean(villagerService, "banVillagers(${body})");



        from("jms:queue:"+jmsPrefix+"loanRequest")
                .bean(loaningService, "createLoan(${body})");
    }
}
