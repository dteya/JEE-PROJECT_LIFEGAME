package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import java.io.IOException;

public class BankingGateway {

    @Inject
    CamelContext camelContext;

    public void emitPension(int amount) {

        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("jms:topic:pension", new Pension(amount), "pensionAmount", amount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
