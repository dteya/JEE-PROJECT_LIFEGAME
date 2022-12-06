package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Random;

@ApplicationScoped
public class ProductDAOImpl implements ProductDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        fr.pantheonsorbonne.ufr27.miage.model.Product newProduct = new fr.pantheonsorbonne.ufr27.miage.model.Product();
        newProduct.setName(product.getName());
        newProduct.setColor(product.getColor());
        newProduct.setShape(product.getShape());
        newProduct.setPrice(product.getPrice());
        newProduct.setLevel(product.getLevel());
        em.persist(newProduct);
        em.flush();
    }

    @Override
    public Collection<fr.pantheonsorbonne.ufr27.miage.model.Product> getAllProducts() {
        return em.createQuery("select p from Product p").getResultList();
    }
}
