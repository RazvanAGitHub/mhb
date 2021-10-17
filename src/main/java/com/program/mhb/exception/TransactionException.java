package com.program.mhb.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
public class TransactionException extends Exception {

    @Getter
    private String message;

    @Getter
    private int code;

    public TransactionException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
