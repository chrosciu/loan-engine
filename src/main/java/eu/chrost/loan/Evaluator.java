package eu.chrost.loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Evaluator {
    public Response processLoanRequest(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(20_000)) > 0) {
            return Response.refusal("Amount is too big");
        }
        if (request.getAmount().compareTo(BigDecimal.valueOf(5_000)) > 0 && request.getPeriod().getYears() > 1) {
            return Response.suspension(List.of("Employee reference"), LocalDate.now().plusDays(10));
        }
        return Response.approval(request.getAmount().multiply(BigDecimal.valueOf(0.8)));
    }
}
