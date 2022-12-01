package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Loan;
import fr.pantheonsorbonne.ufr27.miage.service.LoaningService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("loan")
public class LoanResource {

    @Inject
    LoaningService loaningService;

    @Path("/")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Loan> getLoans() {
        return loaningService.getAllLoans();
    }

    @Path("{loanId}/accept")
    @PUT
    public void acceptLoan(@PathParam("loanId") int loanId) {
        loaningService.acceptLoan(loanId);
    }

    @Path("{loanId}/decline")
    @PUT
    public void declineLoan(@PathParam("loanId") int loanId) {
        loaningService.declineLoan(loanId);
    }

}
