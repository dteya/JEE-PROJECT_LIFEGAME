package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class BankingGateway {

    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void emitPension(int amount) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            Pension pension = new Pension(amount);
            producerTemplate.sendBody("jms:topic:pension", amount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
