package fr.pantheonsorbonne.ufr27.miage.camel;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.service.BlockchainService;
import fr.pantheonsorbonne.ufr27.miage.service.ProductService;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.support.DefaultComponent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    ProductService productService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    BlockchainService blockchainService;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

       from("scheduler://productGen?delay=30000")
               .bean(productService, "createProduct()")
               .log("Product created")
               .log("tokenId : ${body}")
               .bean(blockchainService, "getFunctionABI(${body})")
               .log("encodedData : ${body}")
               .toD("web3j://http://localhost:7545?operation=ETH_SEND_TRANSACTION&fromAddress=0x5d4ac65059bd3b573777ebac62df0dff49375785&toAddress=0x6A08449C90424141b138d459baDC0850f55BC01e&gasLimit=6721975&gasPrice=20000000000&data=${body}");

       from("scheduler://productPub?delay=30000")
                .bean(productService, "publishProducts()")
                .log("Merchandise update")
                .to("jms:topic:"+jmsPrefix+"merchandise");

       from("jms:queue:"+jmsPrefix+"purchaseCounter")
                .setBody(method(productService, "validatePurchase(${body}, ${headers.idVillager})"))
                .log("Purchase validated for villager ${headers.idVillager} replying to ${headers}")
                .to("jms:queue:"+jmsPrefix+"purchaseReceipt");

    }

    private static class ExpiredTransitionalTicketProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            //https://camel.apache.org/manual/exception-clause.html
            CamelExecutionException caused = (CamelExecutionException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);

            exchange.getMessage().setBody(((ExpiredTransitionalTicketException) caused.getCause()).getExpiredTicketId());
        }
    }
}
