package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Loan implements Serializable {

    int loanAmount;

    String status;

    public Loan(int loanAmount, String status) {
        this.loanAmount = loanAmount;
        this.status = status;
    }

    public Loan() {
    }


    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
