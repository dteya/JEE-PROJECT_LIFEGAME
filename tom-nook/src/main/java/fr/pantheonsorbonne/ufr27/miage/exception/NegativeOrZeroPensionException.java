package fr.pantheonsorbonne.ufr27.miage.exception;

public class NegativeOrZeroPensionException extends Exception {

    private int amount;

    public NegativeOrZeroPensionException (int amount) {
        this.amount = amount;
    }

}
