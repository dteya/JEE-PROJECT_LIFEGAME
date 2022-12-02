package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Product;

import java.util.Collection;
import java.util.Random;

public class ProductDAOImpl implements ProductDAO{

    Random rand = new Random();
    int minPrice = 20;
    int maxPrice = 200;
    int minLevel = 1;
    int maxLevel = 3;

    int Price = rand.nextInt(maxPrice - minPrice + 1) + minPrice;
    int Level = rand.nextInt(maxLevel - minLevel + 1) + minLevel;

    @Override
    public Boolean publishProduct(Product product) {
        return null;
    }

    @Override
    public Collection<Product> getAllProducts() {
        return null;
    }
}
