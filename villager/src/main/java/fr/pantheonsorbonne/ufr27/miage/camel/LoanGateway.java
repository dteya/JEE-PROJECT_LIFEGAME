package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;
import fr.pantheonsorbonne.ufr27.miage.service.LoaningService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class LoanGateway {

    @Inject
    LoaningService loaningService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void emitLoanRequest(Loan loan) {
        try(ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody(
                    "jms:queue:"+jmsPrefix+"loanRequest",
                    new fr.pantheonsorbonne.ufr27.miage.dto.Loan(
                            loan.getLoanAmount(),
                            LoanStatus.WAITING.toString(),
                            loan.getId()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
