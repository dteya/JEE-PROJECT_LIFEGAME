package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import java.util.Collection;

public interface LoaningService {

    Collection<Loan> getAllLoans();

    void emitLoanRequest();

    boolean updateLoan(fr.pantheonsorbonne.ufr27.miage.dto.Loan loan);

    boolean checkWaitingLoan(int villagerId);

}
