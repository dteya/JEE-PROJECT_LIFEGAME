package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.LoanStatus;
import fr.pantheonsorbonne.ufr27.miage.exception.CannotUpdateLoanException.LoanAlreadyProcessedException;
import fr.pantheonsorbonne.ufr27.miage.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class LoanDAOImpl implements LoanDAO {

    @PersistenceContext
    EntityManager em;

    @Inject
    VillagerDAO villagerDAO;

    @Override
    @Transactional
    public Collection<Loan> getAllLoans() {
        return em.createNamedQuery("Loan.findAll", Loan.class).getResultList();
    }

    @Transactional
    @Override
    public Loan acceptLoan(int loanId, String status) throws LoanAlreadyProcessedException {
        Loan loan = em.createNamedQuery("Loan.findOne", Loan.class)
                .setParameter("loanId", loanId)
                .getSingleResult();
        if(!loan.getLoanStatus().equals(LoanStatus.WAITING.toString())) {
            throw new LoanAlreadyProcessedException(loanId);
        }
        loan.setLoanStatus(status);

        return loan;
    }

    @Override
    @Transactional
    public void createLoan(int amount, int villagerId) {
        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setIdVillager(villagerDAO.getVillager(villagerId));
        loan.setLoanStatus(LoanStatus.WAITING.toString());

        em.persist(loan);
        em.flush();
    }
}
