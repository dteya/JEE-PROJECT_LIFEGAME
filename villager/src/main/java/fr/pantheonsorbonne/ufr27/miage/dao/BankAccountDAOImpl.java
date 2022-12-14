package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BankAccount;
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
    public int getBalance(int idVillager) {
        return (Integer) em.createQuery("select b.balance from BankAccount b where b.owner.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .getSingleResult();
    }

    @Override
    @Transactional
    public boolean creditBankAccount(int villagerId, int amount) {
        BankAccount bankAccount = em.createNamedQuery("BankAccount.findByVillagerId", BankAccount.class)
                .setParameter("villagerId", villagerId)
                .getSingleResult();
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return bankAccount.getBalance() < 0;
    }

    @Override
    @Transactional
    public boolean debitBankAccount(int villagerId, int amount) {
        BankAccount bankAccount = em.createNamedQuery("BankAccount.findByVillagerId", BankAccount.class)
                .setParameter("villagerId", villagerId)
                .getSingleResult();
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        return bankAccount.getBalance() < 0;
    }
}