package com.program.mhb.exception;

public enum AccountErrorDetails {

    ACCOUNT_IBAN_INVALID(4061, "Account IBAN is invalid"),
    ACCOUNT_CURRENCY_INVALID(4062, "Account Currency is invalid");

    private final int value;
    private final String reasonPhrase;

    AccountErrorDetails(int value, String reasonPhrase) {
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
