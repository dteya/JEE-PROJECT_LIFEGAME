package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Pension;
import fr.pantheonsorbonne.ufr27.miage.dto.Villager;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class HousingGateway {

    @Inject
    CamelContext camelContext;

    public void upgradeHouse(int idVillager){
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("jms:queue:upgrade-level", new Villager(idVillager));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
