package com.program.mhb.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
public class AccountException extends Exception {

    @Getter
    private String message;

    @Getter
    private int code;

    public AccountException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
