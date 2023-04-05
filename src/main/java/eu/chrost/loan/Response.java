package eu.chrost.loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Response {
    static Response approval(BigDecimal amount) {
        return new Approval(amount);
    }

    static Response refusal(String reason) {
        return new Refusal(reason);
    }

    static Response suspension(List<String> additionalRequirements, LocalDate deadline) {
        return new Suspension(additionalRequirements, deadline);
    }
}
