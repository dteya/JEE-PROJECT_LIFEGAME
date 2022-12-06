package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdVillager")
    private Villager idVillager;

    @Column(name = "inventoryCapacity")
    private Integer inventoryCapacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Villager getIdVillager() {
        return idVillager;
    }

    public void setIdVillager(Villager idVillager) {
        this.idVillager = idVillager;
    }

    public Integer getInventoryCapacity() {
        return inventoryCapacity;
    }

    public void setInventoryCapacity(Integer inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }

}