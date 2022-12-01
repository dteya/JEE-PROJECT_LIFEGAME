package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@ApplicationScoped
public class LoanDAOImpl implements LoanDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Collection<Loan> getAllLoans() {
        return em.createNamedQuery("Loan.findAll", Loan.class).getResultList();
    }

    @Override
    public void acceptLoan(int loanId) {
        em.createNamedQuery("Loan.updateStatus", Loan.class)
                .setParameter("loanStatus", LoanStatus.ACCEPTED)
                .setParameter("loanId", loanId)
                .executeUpdate();
    }

    @Override
    public void declineLoan(int loanId) {
        em.createNamedQuery("Loan.updateStatus", Loan.class)
                .setParameter("loanStatus", LoanStatus.DECLINED)
                .setParameter("loanId", loanId)
                .executeUpdate();
    }
}
