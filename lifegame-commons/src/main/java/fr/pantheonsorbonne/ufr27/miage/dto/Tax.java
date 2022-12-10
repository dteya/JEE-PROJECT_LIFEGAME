package fr.pantheonsorbonne.ufr27.miage.dto;

import java.io.Serializable;

public class Tax implements Serializable {

    int amountTax;

    public Tax(int amountTax) {
        this.amountTax = amountTax;
    }

    public int getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(int amountTax) {
        this.amountTax = amountTax;
    }
}
