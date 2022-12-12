package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.LoanGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.LoanDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class LoaningServiceImpl implements LoaningService {

    @Inject
    LoanDAO loanDAO;

    @Inject
    VillagerDAO villagerDAO;

    @Inject
    LoanGateway loanGateway;

    @Inject
    BankingService bankingService;

    @Override
    public Collection<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @Override
    public void emitLoanRequest(int villagerId, int amount) {
        if(loanDAO.hasWaitingLoan(villagerId)) {
            Loan loan = loanDAO.createLoan(amount, villagerDAO.getVillager(villagerId));
            loanGateway.emitLoanRequest(loan);
        }
    }

    @Override
    public boolean updateLoan(fr.pantheonsorbonne.ufr27.miage.dto.Loan loan) {
        loanDAO.acceptLoan(loan.getLoanId(), loan.getStatus());
        return loan.getStatus().equals(LoanStatus.ACCEPTED.toString());
    }

    @Override
    public boolean checkWaitingLoan(int villagerId) {
        return loanDAO.hasWaitingLoan(villagerId);
    }


}
