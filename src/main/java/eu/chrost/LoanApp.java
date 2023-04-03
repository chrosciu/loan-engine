package eu.chrost;

import eu.chrost.loan.Evaluator;
import eu.chrost.loan.Request;

import java.math.BigDecimal;
import java.time.Period;

public class LoanApp {
    public static void main(String[] args) {
        var evaluator = new Evaluator();

        var request = new Request(BigDecimal.valueOf(10_000), Period.ofYears(2));

        var response = evaluator.processLoanRequest(request);

        switch (response.getType()) {
            case APPROVAL -> System.out.printf("Loan approved, amount granted: %.2f%n",
                    response.getAmount().doubleValue());
            case REFUSAL -> System.out.printf("Loan refused due to: %s%n", response.getReason());
            case SUSPENSION -> {
                System.out.println("Loan processing suspended.");
                System.out.println("Following additional requirements are needed to make final decision: ");
                for (String requirement : response.getAdditionalRequirements()) {
                    System.out.println(requirement);
                }
                System.out.printf("Deadline to fulfill requirements mentioned above: %s%n", response.getDeadline());
            }
        }
    }
}
