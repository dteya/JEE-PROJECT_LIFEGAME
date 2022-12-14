package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class VillagerDAOImpl implements VillagerDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public Collection<Villager> listAllVillager() {
        return em.createQuery("SELECT v from Villager v").getResultList();
    }

    @Override
    @Transactional
    public void levelUpVillager(int idVillager) {
        em.createQuery("update Villager v set v.level = v.level +1 where v.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void banVillager(int idVillager, boolean banStatus) {
        em.createQuery("update Villager v set v.bannedStatus = :banStatus where v.id = :idVillager")
                .setParameter("banStatus", banStatus)
                .setParameter("idVillager", idVillager)
                .executeUpdate();
    }



    @Override
    @Transactional
    public Villager getVillager(int villagerId) {
        return (Villager) em.createQuery("SELECT v FROM Villager v WHERE id = :villagerId")
                .setParameter("villagerId", villagerId)
                .getSingleResult();
    }
}
