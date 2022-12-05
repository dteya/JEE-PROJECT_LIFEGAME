package fr.pantheonsorbonne.ufr27.miage.camel;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.service.ProductService;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    ProductService productService;


    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("scheduler://foo?delay=5000")
                .bean(productService, "createProduct()")
                .log("Product created");

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
