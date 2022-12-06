package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Merchandise;
import fr.pantheonsorbonne.ufr27.miage.dto.Product;

import java.util.Collection;

public interface ProductService {

    boolean findLikedProduct(Product product, int idVillager);

    void purchaseProducts(Merchandise merchandise, int idVillager);

    Merchandise scavengeMerchandise(Merchandise merchandise, int idVillager);
}
