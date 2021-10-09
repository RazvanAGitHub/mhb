package com.program.mhb.domain;

public enum Currency {

    RON("RON"),
    EUR("EUR"),
    USD("USD");

    private final String type;

    Currency(String type) {
        this.type = type;
    }
}
