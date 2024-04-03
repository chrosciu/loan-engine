package eu.chrost.loan;

import static java.util.FormatProcessor.FMT;

public class Formatter {
    public String formatResponse(Response response, Request request) {
        return switch (response) {
            case Approval(var amount) when amount.compareTo(request.amount()) >= 0 -> "Loan approved, granted full amount";
            case Approval(var amount) -> FMT."Loan approved, amount granted: %.2f\{amount.doubleValue()}";
            case Refusal(var reason) -> STR."Loan refused due to: \{reason}";
            case Suspension(var additionalRequirements, var deadline) -> STR.
                    """
                    Loan processing suspended.
                    Following additional requirements are needed to make final decision:
                    \{String.join(System.lineSeparator(), additionalRequirements)}
                    Deadline to fulfill requirements mentioned above: \{deadline}
                    """;
        };
    }
}
