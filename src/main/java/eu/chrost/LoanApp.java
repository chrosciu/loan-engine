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

        switch (response) {
            case Approval approval:
                if (approval.amount().compareTo(request.amount()) >= 0) {
                    System.out.println("Loan approved, granted full amount");
                } else {
                    System.out.printf("Loan approved, amount granted: %.2f%n", approval.amount().doubleValue());
                }
                break;
            case Refusal refusal:
                System.out.printf("Loan refused due to: %s%n", refusal.reason());
                break;
            case Suspension suspension:
                System.out.println("Loan processing suspended.");
                System.out.println("Following additional requirements are needed to make final decision: ");
                for (String requirement : suspension.additionalRequirements()) {
                    System.out.println(requirement);
                }
                System.out.printf("Deadline to fulfill requirements mentioned above: %s%n", suspension.deadline());
                break;
            default:
                throw new IllegalStateException("Unknown response type");
        }
    }
}
