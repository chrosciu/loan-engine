package eu.chrost.loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Evaluator {
    public Response processLoanRequest(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(20_000)) > 0) {
            return new Refusal("Amount is too big");
        }
        if (request.getAmount().compareTo(BigDecimal.valueOf(5_000)) > 0 && request.getPeriod().getYears() > 1) {
            return new Suspension(List.of("Employee reference"), LocalDate.now().plusDays(10));
        }
        return new Approval(request.getAmount().multiply(BigDecimal.valueOf(0.8)));
    }
}
