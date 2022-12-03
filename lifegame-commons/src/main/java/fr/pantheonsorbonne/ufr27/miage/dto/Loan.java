package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Loan implements Serializable {

    int loanAmount;

    String status;

    int loanId;

    public Loan(int loanAmount, String status, int loanId) {
        this.loanAmount = loanAmount;
        this.status = status;
        this.loanId = loanId;
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

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
}
