package com.program.mhb.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private String message;

    @Getter
    private int code;

    public NotFoundException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
