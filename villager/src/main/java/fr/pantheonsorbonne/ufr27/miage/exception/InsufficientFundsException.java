package fr.pantheonsorbonne.ufr27.miage.exception;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(int idVillager) {

        super("not enough funds for the villager" + idVillager);
    }

}
