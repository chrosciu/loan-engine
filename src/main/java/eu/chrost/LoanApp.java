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
            case Approval(var amount) when amount.compareTo(request.amount()) >= 0 ->
                    System.out.println("Loan approved, granted full amount");
            case Approval(var amount) ->
                    System.out.printf("Loan approved, amount granted: %.2f%n", amount.doubleValue());
            case Refusal _ -> System.out.println("Loan refused");
            case Suspension(var additionalRequirements, var _) -> {
                System.out.println("Loan processing suspended.");
                System.out.println("Following additional requirements are needed to make final decision: ");
                for (String requirement : additionalRequirements) {
                    System.out.println(requirement);
                }
            }
        }
    }
}
