package com.example.jiw;

import lombok.Getter;

@Getter
public class AdsException extends Exception {

    private String message;
    private int code;

    public AdsException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
