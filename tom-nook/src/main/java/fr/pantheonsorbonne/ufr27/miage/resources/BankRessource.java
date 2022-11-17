package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.BankingService;

import javax.inject.Inject;
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
    public void emitPension(@PathParam("value") int pensionValue) {
            bankingService.emitPension(pensionValue);
    }


}
