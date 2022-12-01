package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.LoanDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class LoaningServiceImpl implements LoaningService {

    @Inject
    LoanDAO loanDAO;

    @Override
    public Collection<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @Override
    public void acceptLoan(int loanId) {
        loanDAO.acceptLoan(loanId);
    }

    @Override
    public void declineLoan(int loanId) {
        loanDAO.declineLoan(loanId);
    }
}
