package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.LoanGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.LoanDAO;
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
    public void acceptLoan(int loanId, String status) {
        System.out.println(loanId + " " + status);
        Loan loan = loanDAO.acceptLoan(loanId, status);
        loanGateway.emitLoanResponse(loan);
    }
}
