package eu.chrost.loan;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class Suspension implements Response {
    List<String> additionalRequirements;
    LocalDate deadline;
}
