package com.program.mhb.domain;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String type;

    Status(String type) {
        this.type = type;
    }
}
