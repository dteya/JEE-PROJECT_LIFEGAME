package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class VillagerDAOImpl implements VillagerDAO{

    final int lvl = 1;
    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public int getLvlVillager(int idVillager) {
        return (Integer) em.createQuery("select s.level from Villager s where Villager.id = :idVillager")
                .setParameter("idVillager", idVillager).getSingleResult();
    }

    @Override
    @Transactional
    public boolean updateLvlVillager(int idVillager) {
        em.createQuery("update Villager v set v.level = v.level + :lvl where v.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .executeUpdate();
        return true;
    }
}
