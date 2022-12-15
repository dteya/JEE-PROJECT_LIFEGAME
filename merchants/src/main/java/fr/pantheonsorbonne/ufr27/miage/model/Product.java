package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "Name", length = 30)
    private String name;

    @Size(max = 30)
    @Column(name = "Shape", length = 30)
    private String shape;

    @Size(max = 30)
    @Column(name = "Color", length = 30)
    private String color;

    @Column(name = "Level")
    private Integer level;

    @Column(name = "Price")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}