package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ProductDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Merchandise;
import fr.pantheonsorbonne.ufr27.miage.dto.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger("logger");

    enum Name {table, bureau, miroir, etagere, chaise, tableau, tapis, porte, armoire, canape;
        private static final Random RandomName = new Random();

        public static String randomName()  {
            Name[] name = values();
            return name[RandomName.nextInt(name.length)].toString();
        }
    }

    //, rond, triangle, rectangle, losange
    enum Shape {carre;
        private static final Random RandomForme = new Random();

        public static String randomForme()  {
            Shape[] shapes = values();
            return shapes[RandomForme.nextInt(shapes.length)].toString();
        }

    }

    //, bleu , noir, jaune, orange, blanc, rose
    enum Color {rouge;
        private static final Random RandomColor = new Random();

        public static String randomColor()  {
            Color[] colors = values();
            return colors[RandomColor.nextInt(colors.length)].toString();
        }
    }

    Random rand = new Random();
    final int minPrice = 30;
    final int maxPrice = 500;
    final int minLevel = 1;
    final int maxLevel = 2;

    @Inject
    ProductDAO productDAO;

    
    @Override
    public Merchandise publishProducts() {
        Collection<Product> merchandise = new ArrayList<>();

        productDAO.getAllProducts().forEach(
                (fr.pantheonsorbonne.ufr27.miage.model.Product product) -> merchandise.add(new Product(product.getName(), product.getShape(), product.getColor(), product.getLevel(), product.getPrice())));

        LOGGER.info("New merchandises are published by the merchant");
        return new Merchandise(merchandise);
    }

    @Override
    public Product createProduct() {
        String randomName = Name.randomName();
        String randomShape = Shape.randomForme();
        String randomColor = Color.randomColor();
        int randPrice = rand.nextInt(maxPrice - minPrice + 1) + minPrice;
        int randLevel = rand.nextInt(maxLevel - minLevel + 1) + minLevel;
        Product product = new Product(randomName, randomShape, randomColor, randLevel, randPrice);
        LOGGER.info("Merchant has created " +product.getName()+" "+product.getColor()+" "+product.getShape());
        productDAO.saveProduct(product);
        return product;
    }

    @Override
    public Merchandise validatePurchase(Merchandise merchandise, int idVillager) {
        merchandise.getMerchandise().forEach((Product product) -> productDAO.remove(product));
        merchandise.getMerchandise().forEach(
                (Product product) -> LOGGER.info(
                    product.getName() + " " + product.getShape() + " " + product.getColor() + " has been sold to Villager #" + idVillager
                )
        );
        return merchandise;
    }

    @Override
    public fr.pantheonsorbonne.ufr27.miage.model.Product getProduct(int productId){
        return productDAO.getProduct(productId);

    }

}
