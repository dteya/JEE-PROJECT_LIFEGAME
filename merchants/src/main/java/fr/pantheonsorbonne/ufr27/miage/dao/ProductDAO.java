package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Product;

import java.util.Collection;

public interface ProductDAO {

    void saveProduct(Product product);

    void remove(Product product);

    Collection<fr.pantheonsorbonne.ufr27.miage.model.Product> getAllProducts();
}
