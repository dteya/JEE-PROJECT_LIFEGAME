package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Product implements Serializable {

    int id;
    String name;
    String shape;
    String color;
    int level;
    int price;

    public Product(int id, String name, String shape, String color, int level, int price) {
        this.id = id;
        this.name = name;
        this.shape = shape;
        this.color = color;
        this.level = level;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
