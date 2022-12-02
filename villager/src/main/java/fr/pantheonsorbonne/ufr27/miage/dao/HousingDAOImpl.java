package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HousingDAOImpl implements HousingDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public int getAmountBankAccount(int idVillager) {
        return (Integer) em.createQuery("Select s.balance from BankAccount s where Villager.id = :idVillager ")
                .setParameter("idVillager", idVillager).getSingleResult();
    }
}
