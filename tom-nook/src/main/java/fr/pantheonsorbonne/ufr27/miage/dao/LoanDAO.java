package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import java.util.Collection;

public interface LoanDAO {

    Collection<Loan> getAllLoans();

    Loan acceptLoan(int loanId, String status) throws CannotUpdateLoanException.LoanAlreadyProcessedException, CannotUpdateLoanException.LoanNotFoundException;

    void createLoan(int amount, int villagerId);

}
