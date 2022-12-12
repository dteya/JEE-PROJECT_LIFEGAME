package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Tax;
import fr.pantheonsorbonne.ufr27.miage.dto.Villager;
import fr.pantheonsorbonne.ufr27.miage.dto.Villagers;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

@ApplicationScoped
public class VillagersGateway {

    @Inject
    CamelContext camelContext;

    public void sendVillagers(Collection<Villager> villagers) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("jms:queue:villagersInDebt", new Villagers(villagers));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
