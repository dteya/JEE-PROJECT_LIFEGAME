package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException.LoanAlreadyProcessedException;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import java.util.Collection;

public interface LoanDAO {

    Collection<Loan> getAllLoans();

    Loan acceptLoan(int loanId, String status) throws LoanAlreadyProcessedException;

    void createLoan(int amount, int villagerId);

}
