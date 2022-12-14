package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dto.Merchandise;
import fr.pantheonsorbonne.ufr27.miage.dto.Product;

public interface ProductService {
    Merchandise publishProducts();

    Merchandise validatePurchase(Merchandise products, int idVillager);

    Product createProduct();

    fr.pantheonsorbonne.ufr27.miage.model.Product getProduct(int productId);
}
