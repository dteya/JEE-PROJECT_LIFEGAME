package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.BankingService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("bank")
public class BankRessource {

    @Inject
    BankingService bankingService;

    @Path("/pension/{value}")
    @POST
    public Response emitPension(@PathParam("value") int pensionValue) {
        if (pensionValue <= 0) {
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
            bankingService.emitPension(pensionValue);
        return Response.status(Response.Status.OK).build();
    }

    @Path("/tax")
    @POST
    public Response collectTax(){
        bankingService.collectTax();
        return Response.status(Response.Status.OK).build();
    }

}
