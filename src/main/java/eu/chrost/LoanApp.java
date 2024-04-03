import eu.chrost.loan.Evaluator;
import eu.chrost.loan.Formatter;
import eu.chrost.loan.Request;

import java.math.BigDecimal;
import java.time.Period;

void main() {
    var evaluator = new Evaluator();

    var request = new Request(BigDecimal.valueOf(10_000), Period.ofYears(2));

    var response = evaluator.processLoanRequest(request);

    var formatter = new Formatter();

    System.out.println(formatter.formatResponse(response, request));
}

