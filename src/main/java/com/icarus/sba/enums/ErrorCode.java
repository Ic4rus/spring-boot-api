package com.icarus.sba.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SUCCESS(0, "Success");

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
