package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import java.util.Collection;

public interface ProductService {
    Collection<Product> publishProducts();

    Product generateProduct();
}
