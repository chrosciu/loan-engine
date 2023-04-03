package eu.chrost.loan;

import lombok.Value;

@Value
public class Refusal implements Response {
    String reason;
}
