package eu.chrost.loan;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Period;

@Value
public class Request {
    @NonNull
    BigDecimal amount;
    @NonNull
    Period period;
}
