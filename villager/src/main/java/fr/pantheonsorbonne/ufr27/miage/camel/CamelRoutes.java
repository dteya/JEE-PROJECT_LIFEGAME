package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import fr.pantheonsorbonne.ufr27.miage.service.HousingService;
import fr.pantheonsorbonne.ufr27.miage.service.HousingServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.service.ProductService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
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

import static org.apache.camel.component.pdf.PdfHeaderConstants.PROTECTION_POLICY_HEADER_NAME;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.mailAppPassword")
    String mailAppPassword;

    @Inject
    BankingService bankingService;

    @Inject
    ProductService productService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.villagerId")
    Integer idVillager;

    @Inject
    CamelContext camelContext;

    @Inject
    HousingService housingService;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("jms:topic:pension")
                .log("pension: ${headers}")
                .bean(bankingService, "creditBankAccount(${body})")

        ;

        from("jms:topic:merchandise")
                .setHeader("idVillager", constant(idVillager))
                .setBody(method(productService, "scavengeMerchandise(${body}, ${headers.idVillager})"))
                .choice().when(body().isNotNull())
                .to("jms:queue:purchaseCounter")

        ;

        from("jms:queue:purchaseReceipt")
                .bean(productService, "purchaseProducts(${body}, ${headers.idVillager})")
                .marshal().json()
                .to("pdf:create")
                .log("receipt : ${headers}")
                .marshal().pgp("file:/Users/teyadidi/miage-2021-jee-project/villager/target/CryptedKey.asc", "Merchant <lifegamemerchant@gmail.com>")
                .to("file:target/crypted?filename=Receipt.pdf.pgp")
                .unmarshal().pgp("file:/Users/teyadidi/miage-2021-jee-project/villager/target/SecretCryptedKey.asc", "Merchant <lifegamemerchant@gmail.com>", "teya2002")
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

        from("jms:topic:tax")
                .log("tax: ${headers}")
                .bean(bankingService, "deductTax(${body})");
    }


}
