package eu.chrost;

import eu.chrost.loan.Approval;
import eu.chrost.loan.Evaluator;
import eu.chrost.loan.Refusal;
import eu.chrost.loan.Request;
import eu.chrost.loan.Suspension;

import java.math.BigDecimal;
import java.time.Period;

public class LoanApp {
    public static void main(String[] args) {
        var evaluator = new Evaluator();

        var request = new Request(BigDecimal.valueOf(10_000), Period.ofYears(2));

        var response = evaluator.processLoanRequest(request);

        if (response instanceof Approval approval) {
            if (approval.getAmount().compareTo(request.getAmount()) >= 0) {
                System.out.println("Loan approved, granted full amount");
            } else {
                System.out.printf("Loan approved, amount granted: %.2f%n", approval.getAmount().doubleValue());
            }
        } else if (response instanceof Refusal refusal) {
            System.out.printf("Loan refused due to: %s%n", refusal.getReason());
        } else if (response instanceof Suspension suspension) {
            System.out.println("Loan processing suspended.");
            System.out.println("Following additional requirements are needed to make final decision: ");
            for (String requirement : suspension.getAdditionalRequirements()) {
                System.out.println(requirement);
            }
            System.out.printf("Deadline to fulfill requirements mentioned above: %s%n", suspension.getDeadline());
        }
    }
}
