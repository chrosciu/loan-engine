package eu.chrost.loan;

import java.time.LocalDate;
import java.util.List;

public record Suspension(List<String> additionalRequirements, LocalDate deadline) implements Response {
}
