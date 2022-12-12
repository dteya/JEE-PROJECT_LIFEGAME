package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class LoanDAOImpl implements LoanDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Collection<Loan> getAllLoans() {
        return em.createNamedQuery("Loan.findAll", Loan.class).getResultList();
    }

    @Transactional
    @Override
    public Loan acceptLoan(int loanId, String status) {
        Loan loan = em.createNamedQuery("Loan.findOne", Loan.class)
                .setParameter("loanId", loanId)
                .getSingleResult();
        loan.setLoanStatus(status);

        return loan;
    }

    @Transactional
    @Override
    public Loan createLoan(int amount, Villager villager) {
        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setIdVillager(villager);
        loan.setLoanStatus(LoanStatus.WAITING.toString());

        em.persist(loan);
        em.flush();

        return loan;
    }

    @Override
    public boolean hasWaitingLoan(int villagerId) {
        return em.createNamedQuery("Loan.findByVillagerStatus", Loan.class)
                .setParameter("villagerId", villagerId)
                .setParameter("status", LoanStatus.WAITING.toString())
                .getResultList().size() == 0;
    }


}
