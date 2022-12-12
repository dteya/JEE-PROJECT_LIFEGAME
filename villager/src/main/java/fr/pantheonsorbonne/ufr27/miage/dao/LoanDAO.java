package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Loan;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import java.util.Collection;

public interface LoanDAO {

    Collection<Loan> getAllLoans();

    Loan acceptLoan(int loanId, String status);

    Loan createLoan(int amount, Villager villager);

    boolean hasWaitingLoan(int villagerId);

}
