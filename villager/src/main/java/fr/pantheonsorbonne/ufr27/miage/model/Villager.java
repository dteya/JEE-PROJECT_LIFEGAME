package fr.pantheonsorbonne.ufr27.miage.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Villager")
public class Villager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 36)
    @Column(name = "name", length = 36)
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "bannedStatus")
    @ColumnDefault("false")
    private Boolean bannedStatus = false;

    public Boolean getBannedStatus() {
        return bannedStatus;
    }

    public void setBannedStatus(Boolean bannedStatus) {
        this.bannedStatus = bannedStatus;
    }

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}