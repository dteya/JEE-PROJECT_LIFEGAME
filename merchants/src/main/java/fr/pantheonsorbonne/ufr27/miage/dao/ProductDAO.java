package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Product;

import java.util.Collection;

public interface ProductDAO {

    Boolean publishProduct(Product product);

    Collection<Product> getAllProducts();
}
