package fr.pantheonsorbonne.ufr27.miage.dto;

public class Loan {

    int villagerId;

    int loanAmount;

    LoanStatus status;

    public Loan(int villagerId, int loanAmount, LoanStatus status) {
        this.villagerId = villagerId;
        this.loanAmount = loanAmount;
        this.status = status;
    }

    public Loan() {
    }

    public int getVillagerId() {
        return villagerId;
    }

    public void setVillagerId(int villagerId) {
        this.villagerId = villagerId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
