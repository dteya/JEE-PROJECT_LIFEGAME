package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Wishlist;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class WishlistDAOImpl implements WishlistDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Override
    @Transactional
    public Wishlist getWishlist(int idVillager) {
        return (Wishlist) em.createQuery("select w from Wishlist w where w.villager.id = :idVillager")
                .setParameter("idVillager", idVillager)
                .getSingleResult();
    }
}
