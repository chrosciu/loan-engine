package eu.chrost.loan;

public sealed interface Response permits Approval, Refusal, Suspension {
}
