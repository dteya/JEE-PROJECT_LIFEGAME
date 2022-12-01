package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import java.util.Collection;

public interface LoanDAO {

    Collection<Loan> getAllLoans();

    void acceptLoan(int loanId);

    void declineLoan(int loanId);

}
