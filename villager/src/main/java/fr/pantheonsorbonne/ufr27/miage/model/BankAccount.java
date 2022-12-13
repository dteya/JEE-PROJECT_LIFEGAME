package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.listener.BankAccountListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(BankAccountListener.class)
@Table(name = "BankAccount")
@NamedQueries({
        @NamedQuery(
                name="BankAccount.findAll",
                query="SELECT b FROM BankAccount b"
        ),
        @NamedQuery(
                name="BankAccount.findByVillagerId",
                query="SELECT b FROM BankAccount b WHERE b.owner.id = :villagerId"
        )
})
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ownerId", nullable = false)
    private Villager owner;

    @Column(name = "balance")
    private Integer balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Villager getOwner() {
        return owner;
    }

    public void setOwner(Villager owner) {
        this.owner = owner;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}