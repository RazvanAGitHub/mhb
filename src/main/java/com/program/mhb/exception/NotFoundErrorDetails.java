package com.program.mhb.exception;

public enum NotFoundErrorDetails {

    NOT_FOUND_ID_INVALID(6061, "ID not found");

    private final int value;
    private final String reasonPhrase;

    NotFoundErrorDetails(int value, String reasonPhrase) {
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
