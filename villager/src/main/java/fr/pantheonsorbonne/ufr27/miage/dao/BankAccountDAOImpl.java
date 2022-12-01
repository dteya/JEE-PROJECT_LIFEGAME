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

        System.out.println(
                em.createQuery("select b from BankAccount b").getResultList().size()
        );
        em.createQuery("update BankAccount b set b.balance = b.balance + :amount")
                .setParameter("amount", amount)
                .executeUpdate();
        //System.out.println(em.createQuery("select b from BankAccount b where b.owner = '1' ").getSingleResult());
        return true;
    }
}