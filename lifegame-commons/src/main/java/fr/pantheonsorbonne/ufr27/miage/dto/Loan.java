package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Loan implements Serializable {

    int loanAmount;

    String status;

    int loanId;

    int villagerId;

    public Loan(int loanAmount, String status, int loanId, int villagerId) {
        this.loanAmount = loanAmount;
        this.status = status;
        this.loanId = loanId;
        this.villagerId = villagerId;
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

    public int getVillagerId() {
        return villagerId;
    }

    public void setVillagerId(int villagerId) {
        this.villagerId = villagerId;
    }
}
