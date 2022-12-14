package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.LoanGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.LoanDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException.LoanAlreadyProcessedException;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class LoaningServiceImpl implements LoaningService {

    @Inject
    LoanDAO loanDAO;

    @Inject
    LoanGateway loanGateway;

    @Override
    public Collection<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @Override
    public void acceptLoan(int loanId, String status) throws LoanAlreadyProcessedException, CannotUpdateLoanException.LoanNotFoundException {
        System.out.println(loanId + " " + status);
        Loan loan = loanDAO.acceptLoan(loanId, status);
        loanGateway.emitLoanResponse(loan);
    }

    @Override
    public void createLoan(fr.pantheonsorbonne.ufr27.miage.dto.Loan loan) {
        loanDAO.createLoan(loan.getLoanAmount(), loan.getVillagerId());
    }
}
