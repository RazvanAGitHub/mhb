package com.program.mhb.exception;

public enum TransactionErrorDetails {

    TRANSACTION_INSUFFICIENT_FOUNDS(5061, "Insufficient founds in your account"),
    TRANSACTION_END_DATE_TIME_INVALID(5062, "End Date Time should be after Start Date Time");

    private final int value;
    private final String reasonPhrase;

    TransactionErrorDetails(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
