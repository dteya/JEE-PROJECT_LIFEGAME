package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;

@Entity
@Table(name = "Loan")

@NamedQueries({
        @NamedQuery(
                name="Loan.findAll",
                query="SELECT l FROM Loan l"
        ),
        @NamedQuery(
                name="Loan.findOne",
                query="SELECT l FROM Loan l WHERE l.id = :loanId"
        )
})
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "idVillager", nullable = false)
    private Villager idVillager;

    public Villager getIdVillager() {
        return idVillager;
    }

    public void setIdVillager(Villager idVillager) {
        this.idVillager = idVillager;
    }

    @Column(name = "loanAmount")
    private Integer loanAmount;

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Column(name = "loanStatus")
    private String loanStatus;

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}
