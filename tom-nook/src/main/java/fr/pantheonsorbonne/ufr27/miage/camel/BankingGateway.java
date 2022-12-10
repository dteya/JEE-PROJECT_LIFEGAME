package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Tax;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class BankingGateway {

    @Inject
    CamelContext camelContext;

    public void emitPension(int amount) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("jms:topic:pension", new Pension(amount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void collectTax() {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("jms:topic:tax", new Tax(700));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
