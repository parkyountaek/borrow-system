package com.borrow.system.modulecommon.exception;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    USER_ALREADY_EXIST(409, "USER_ALREADY_EXIST"),
    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND"),
    ORGANIZATION_NOT_FOUND(404, "ORGANIZATION_NOT_FOUND"),
    ITEM_NOT_FOUND(404, "ITEM_NOT_FOUND"),
    ITEM_ALREADY_BORROW(409, "ITEM_ALREADY_BORROW"),
    NO_PERMISSION(403, "NO_PERMISSION")
    ;

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
