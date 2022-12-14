package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.LoanGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.LoanDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException.LoanAlreadyProcessedException;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.logging.Logger;

@ApplicationScoped
public class LoaningServiceImpl implements LoaningService {

    @Inject
    LoanDAO loanDAO;

    @Inject
    LoanGateway loanGateway;

    private static final Logger LOGGER = Logger.getLogger("logger");

    @Override
    public Collection<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @Override
    public void acceptLoan(int loanId, String status) throws LoanAlreadyProcessedException, CannotUpdateLoanException.LoanNotFoundException {
        System.out.println(loanId + " " + status);
        Loan loan = loanDAO.acceptLoan(loanId, status);
        loanGateway.emitLoanResponse(loan);
        LOGGER.info("Loan #" + loanId + " has changed to" + status);
    }

    @Override
    public void createLoan(fr.pantheonsorbonne.ufr27.miage.dto.Loan loan) {
        loanDAO.createLoan(loan.getLoanAmount(), loan.getVillagerId());
        LOGGER.info("A loan request from Villager #" + loan.getVillagerId() + " of " + loan.getLoanAmount() + " has been created");
    }
}
