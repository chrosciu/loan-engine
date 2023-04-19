package eu.chrost.loan;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Period;

public record Request(@NonNull BigDecimal amount, @NonNull Period period) {
}
