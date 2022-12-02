package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.model.Product;

import java.util.Collection;
import java.util.Random;

public class ProductServiceImpl implements ProductService{

    enum Name {table, bureau, miroir, etagere, chaise, tableau, tapis, porte, armoire, canape;
        private static final Random RandomName = new Random();

        public static Name randomName()  {
            Name[] name = values();
            return name[RandomName.nextInt(name.length)];
        }
    }

    enum Shape {rond, carre, triangle, rectangle, losange;
        private static final Random RandomForme = new Random();

        public static Shape randomForme()  {
            Shape[] shapes = values();
            return shapes[RandomForme.nextInt(shapes.length)];
        }

    }

    enum Color {bleu, rouge, noir, jaune, orange, blanc, rose;
        private static final Random RandomColor = new Random();

        public static Color randomColor()  {
            Color[] colors = values();
            return colors[RandomColor.nextInt(colors.length)];
        }
    }

    @Override
    public Collection<Product> publishProducts() {
        return null;
    }

    @Override
    public Product generateProduct() {

        Name randomName = Name.randomName();
        Shape randomShape = Shape.randomForme();
        Color randomColor = Color.randomColor();

        Product product = new Product();

        return product;
    }
}
