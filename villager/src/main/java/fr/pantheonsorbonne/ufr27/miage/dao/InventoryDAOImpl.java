package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Inventory;
import javax.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class InventoryDAOImpl implements InventoryDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public void addToInventory(int idVillager) {
        em.createQuery("update Inventory i set i.inventoryCapacity = i.inventoryCapacity + 1 where i.idVillager.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void resetInventory(int idVillager) {
        em.createQuery("update Inventory i set i.inventoryCapacity = 0 where i.idVillager.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .executeUpdate();
    }

    @Override
    @Transactional
    public Inventory getInventory(int idVillager) {
        return (Inventory) em.createQuery("select i from Inventory i where i.idVillager.id = :idVillager")
                .setParameter("idVillager", idVillager).getSingleResult();
    }
}
