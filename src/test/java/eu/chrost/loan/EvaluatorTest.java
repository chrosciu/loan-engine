package eu.chrost.loan;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Period;

import static org.assertj.core.api.Assertions.assertThat;

class EvaluatorTest {
    private final Evaluator evaluator = new Evaluator();

    @Test
    void shouldRejectLoanWithAmountBiggerThan20K() {
        //given
        var request = new Request(BigDecimal.valueOf(30_000), Period.ofYears(5));

        //when
        var response = evaluator.processLoanRequest(request);

        //then
        assertThat(response).isInstanceOfSatisfying(Refusal.class,
                refusal -> assertThat(refusal.reason()).isEqualTo("Amount is too big"));
    }

    @Test
    void shouldApproveLoanWithAmountLessThan5KWithReducedAmount() {
        //given
        var request = new Request(BigDecimal.valueOf(4_000), Period.ofMonths(2));

        //when
        var response = evaluator.processLoanRequest(request);

        //then
        assertThat(response).isInstanceOfSatisfying(Approval.class,
                approval -> assertThat(approval.amount()).isLessThan(BigDecimal.valueOf(4_000))
        );
    }
}