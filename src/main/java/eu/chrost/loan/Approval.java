package eu.chrost.loan;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Approval implements Response {
    BigDecimal amount;
}
