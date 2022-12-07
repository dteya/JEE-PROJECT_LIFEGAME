package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class BankAccountDAOImpl implements BankAccountDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public boolean creditBankAccount(int amount) {
        em.createQuery("update BankAccount b set b.balance = b.balance + :amount")
                .setParameter("amount", amount)
                .executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public int getAmountBankAccount(int idVillager) {
        return (Integer) em.createQuery("Select s.balance from BankAccount s where s.owner.id = :idVillager ")
                .setParameter("idVillager", idVillager).getSingleResult();
    }
}