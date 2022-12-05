package fr.pantheonsorbonne.ufr27.miage.exception;

public class CannotUpdateLoanException extends Exception {

    public static class LoanAlreadyProcessedException extends Throwable {
        public LoanAlreadyProcessedException(int loanId) {
            super("Cannot update for Loan #" + loanId + " as it has already been updated");
        }
    }
}
