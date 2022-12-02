package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import java.util.Collection;

public interface LoaningService {

    Collection<Loan> getAllLoans();

    void acceptLoan(int loanId, String status);

}
