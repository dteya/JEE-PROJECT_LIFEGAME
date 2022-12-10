package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class VillagerDAOImpl implements VillagerDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public boolean updateLvlVillager(int idVillager) {
        em.createQuery("update Villager v set v.level = v.level + 1 where v.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .executeUpdate();
        return true;



    }

    @Override
    @Transactional
    public Villager getVillager(int idVillager) {
        return (Villager) em.createQuery("select v from Villager v where v.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .getSingleResult();

    }

}
