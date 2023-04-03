package eu.chrost.loan;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static eu.chrost.loan.Response.Type.APPROVAL;
import static eu.chrost.loan.Response.Type.REFUSAL;
import static eu.chrost.loan.Response.Type.SUSPENSION;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Response {
    public enum Type {
        APPROVAL,
        REFUSAL,
        SUSPENSION
    }
    private final Type type;
    private final BigDecimal amount;
    private final String reason;
    private final List<String> additionalRequirements;
    private final LocalDate deadline;

    public static Response approval(BigDecimal amount) {
        return Response.builder().type(APPROVAL).amount(amount).build();
    }

    public static Response refusal(String reason) {
        return Response.builder().type(REFUSAL).reason(reason).build();
    }

    public static Response suspension(List<String> additionalRequirements, LocalDate deadline) {
        return Response.builder().type(SUSPENSION)
                .additionalRequirements(additionalRequirements).deadline(deadline).build();
    }


}
