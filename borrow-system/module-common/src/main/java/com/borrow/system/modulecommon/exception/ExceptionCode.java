package com.borrow.system.modulecommon.exception;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    USER_ALREADY_EXIST(409, "USER_ALREADY_EXIST"),
    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND");

    private final int status;
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
