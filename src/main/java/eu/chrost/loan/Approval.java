package eu.chrost.loan;

import java.math.BigDecimal;

public record Approval(BigDecimal amount) implements Response {
}
