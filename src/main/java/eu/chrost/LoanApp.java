import eu.chrost.loan.Approval;
import eu.chrost.loan.Evaluator;
import eu.chrost.loan.Refusal;
import eu.chrost.loan.Request;
import eu.chrost.loan.Suspension;

import static java.lang.IO.println;

void main() {
    var evaluator = new Evaluator();

    var request = new Request(BigDecimal.valueOf(10_000), Period.ofYears(2));

    var response = evaluator.processLoanRequest(request);

    switch (response) {
        case Approval(var amount) when amount.compareTo(request.amount()) >= 0 ->
                println("Loan approved, granted full amount");
        case Approval(var amount) ->
                println(String.format("Loan approved, amount granted: %.2f%n", amount.doubleValue()));
        case Refusal _ -> System.out.println("Loan refused");
        case Suspension(var additionalRequirements, var _) -> {
            println("Loan processing suspended.");
            println("Following additional requirements are needed to make final decision: ");
            for (String requirement : additionalRequirements) {
                println(requirement);
            }
        }
    }
}

