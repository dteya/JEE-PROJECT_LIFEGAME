package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.NegativeOrZeroPensionException;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;

import javax.inject.Inject;
import javax.validation.constraints.NegativeOrZero;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("bank")
public class BankRessource {

    @Inject
    BankingService bankingService;

    @Path("/pension/{value}")
    @POST
    public void emitPension(@PathParam("value") int pensionValue) throws NegativeOrZeroPensionException {
        if (pensionValue <= 0) {
            throw new NegativeOrZeroPensionException(pensionValue);
        }
            bankingService.emitPension(pensionValue);
    }

    @Path("/tax")
    @POST
    public void collectTax(){
        bankingService.collectTax();
    }

}
