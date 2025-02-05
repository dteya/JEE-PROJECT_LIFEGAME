package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import fr.pantheonsorbonne.ufr27.miage.service.ProductService;
import fr.pantheonsorbonne.ufr27.miage.service.LoaningService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.Attachment;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.attachment.DefaultAttachment;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.mailAppPassword")
    String mailAppPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.villagerId")
    Integer idVillager;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.pathToPrivateKey")
    String pathToPrivateKey;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.pathToPublicKey")
    String pathToPublicKey;

    @Inject
    BankingService bankingService;

    @Inject
    ProductService productService;

    @Inject
    LoaningService loaningService;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("jms:topic:"+jmsPrefix+"pension")
                .log("pension: ${headers}")
                .setHeader("idVillager", constant(idVillager))
                .bean(bankingService, "creditPension(${headers.idVillager}, ${body})")

        ;

        from("jms:topic:"+jmsPrefix+"tax")
                .log("tax: ${headers}")
                .setHeader("idVillager", constant(idVillager))
                .bean(bankingService, "deductTax(${body}, ${headers.idVillager})");

       from ("jms:topic:"+jmsPrefix+"merchandise")
                .setHeader("idVillager", constant(idVillager))
                .setBody(method(productService, "scavengeMerchandise(${body}, ${headers.idVillager})"))
                .choice().when(body().isNotNull())
                .to("jms:queue:"+jmsPrefix+"purchaseCounter")
        ;

        from("jms:queue:"+jmsPrefix+"loanAccept")
                .log("loan: ${body}")
                .choice()
                .when(header("villagerId").isEqualTo(idVillager))
                .setHeader("accept", method(loaningService, "updateLoan(${body})"))
                .end()
                .choice()
                .when(header("accept").isEqualTo(true))
                .bean(bankingService, "creditBankAccount(${headers.villagerId}, ${body})")
                .end();

        from("jms:queue:"+jmsPrefix+"purchaseReceipt")
                .bean(productService, "purchaseProducts(${body}, ${headers.idVillager})")
                .marshal().json()
                .to("pdf:create")
                .log("receipt : ${headers}")
                .marshal().pgp("file:"+pathToPublicKey, "Merchant <lifegamemerchant@gmail.com>")
                .to("file:target/crypted?filename=Receipt.pdf.pgp")
                .unmarshal().pgp("file:"+pathToPrivateKey, "Merchant <lifegamemerchant@gmail.com>", "teya2002")
                .to("file:target/uncrypted?filename=Receipt");

        from("file:target/uncrypted")
                .process(new Processor()
                {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to", "diditeya2@gmail.com");
                        exchange.getMessage().setHeader("from", "lifegamemerchant@gmail.com");
                        exchange.getMessage().setBody(simple("Please find attached the receipt for your purchase"));
                        exchange.getMessage().setHeader("subject", "Receipt for purchase");
                        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
                        attMsg.addAttachment("Receipt.pdf", new DataHandler(new FileDataSource(new File("target/uncrypted/Receipt"))));

                    }
                })
                .to("smtps:smtp.gmail.com:465??username=lifegamemerchant@gmail.com&password=" + mailAppPassword)
        ;
    }
}
