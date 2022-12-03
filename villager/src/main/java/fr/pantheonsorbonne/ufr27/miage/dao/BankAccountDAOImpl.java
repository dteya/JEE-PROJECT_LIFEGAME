package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

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
    public boolean debitBankAccount(int amount, int idVillager) {
        em.createQuery("update BankAccount b set b.balance = b.balance - :amount where b.owner.id = :idVillager")
                .setParameter("amount", amount)
                .setParameter("idVillager", idVillager)
                .executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public int getBalance(int idVillager) {
        return (Integer) em.createQuery("select b.balance from BankAccount b where b.owner.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Collection<Villager> getVillagersInDebt() {
        return (Collection<Villager>) em.createQuery("select b.owner from BankAccount b where b.balance < 0")
                .getResultList();
    }

    @Override
    @Transactional
    public void collectTax(int amount) {
        em.createQuery("update BankAccount b set b.balance = b.balance - :amount")
                .setParameter("amount", amount)
                .executeUpdate();
    }


    @Override
    @Transactional
    public boolean creditBankAccount(int villagerId, int amount) {
        em.createQuery("update BankAccount b set b.balance = b.balance + :amount where b.owner.id = :villagerId")
                .setParameter("amount", amount)
                .setParameter("villagerId", villagerId)
                .executeUpdate();
        return true;
    }
}