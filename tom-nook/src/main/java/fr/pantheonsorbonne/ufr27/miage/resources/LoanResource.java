package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException.LoanAlreadyProcessedException;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;
import fr.pantheonsorbonne.ufr27.miage.service.LoaningService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response acceptLoan(@PathParam("loanId") int loanId) {
        try {
            loaningService.acceptLoan(loanId, LoanStatus.ACCEPTED.toString());
        } catch (LoanAlreadyProcessedException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (CannotUpdateLoanException.LoanNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("{loanId}/decline")
    @PUT
    public Response declineLoan(@PathParam("loanId") int loanId) {
        try {
            loaningService.acceptLoan(loanId, LoanStatus.DECLINED.toString());
        } catch (LoanAlreadyProcessedException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (CannotUpdateLoanException.LoanNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

}
