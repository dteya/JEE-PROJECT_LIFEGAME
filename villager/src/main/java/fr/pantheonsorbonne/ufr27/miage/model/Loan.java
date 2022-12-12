package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loanAmount")
    private Integer loanAmount;

    @Size(max = 255)
    @Column(name = "loanStatus")
    private String loanStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idVillager", nullable = false)
    private Villager idVillager;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Villager getIdVillager() {
        return idVillager;
    }

    public void setIdVillager(Villager idVillager) {
        this.idVillager = idVillager;
    }

}