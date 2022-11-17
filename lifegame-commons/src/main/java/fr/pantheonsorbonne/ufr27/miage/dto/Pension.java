package fr.pantheonsorbonne.ufr27.miage.dto;

public class Pension {

    double amount;

    public Pension(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
