package eu.chrost.loan;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Period;

import static org.assertj.core.api.Assertions.assertThat;

class EvaluatorTest {
    private Evaluator evaluator = new Evaluator();

    @Test
    void shouldRejectLoanWithAmountBiggerThan20K() {
        //given
        var request = new Request(BigDecimal.valueOf(30_000), Period.ofYears(5));

        //when
        var response = evaluator.processLoanRequest(request);

        //then
        assertThat(response).satisfies(
                r -> assertThat(r.getType()).isEqualTo(Response.Type.REFUSAL),
                r -> assertThat(r.getReason()).isEqualTo("Amount is too big")
        );
    }

}