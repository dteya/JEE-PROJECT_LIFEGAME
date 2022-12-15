package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class ProductDAOImpl implements ProductDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        fr.pantheonsorbonne.ufr27.miage.model.Product newProduct = new fr.pantheonsorbonne.ufr27.miage.model.Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setColor(product.getColor());
        newProduct.setShape(product.getShape());
        newProduct.setPrice(product.getPrice());
        newProduct.setLevel(product.getLevel());
        em.persist(newProduct);
        em.flush();
    }

    @Override
    @Transactional
    public void remove(Product product) {
        em.createQuery("delete from Product p where p.name = :pName and p.color = :pColor and p.level = :pLevel and p.shape = :pShape and p.price = :pPrice")
                .setParameter("pName", product.getName())
                .setParameter("pColor", product.getColor())
                .setParameter("pPrice", product.getPrice())
                .setParameter("pLevel", product.getLevel())
                .setParameter("pShape", product.getShape())
                .executeUpdate()
        ;

    }


    @Override
    @Transactional
    public Collection<fr.pantheonsorbonne.ufr27.miage.model.Product> getAllProducts() {
        return em.createQuery("select p from fr.pantheonsorbonne.ufr27.miage.model.Product p").getResultList();
    }

    @Override
    @Transactional
    public fr.pantheonsorbonne.ufr27.miage.model.Product getProduct(int productId) {
        return (fr.pantheonsorbonne.ufr27.miage.model.Product) em.createQuery("select p from Product p where p.id = :productId")
                .setParameter("productId", productId)
                .getSingleResult();
    }
}
