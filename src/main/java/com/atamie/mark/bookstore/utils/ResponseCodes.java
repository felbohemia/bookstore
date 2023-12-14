package com.atamie.mark.bookstore.utils;

public enum ResponseCodes {
    _EXIST("201","BOOK WITH GIVEN ISBN ALREADY EXIST"),
    _NOFFOUND("404","REQUESTED RESOURCE NOT FOUND"),
    _SUCCESSFUL("200","REQUEST SUCCESSFULLY EXECUTED"),
    _SERVERERROR("500","INTERNAL SERVER ERROR OCCURRED");

    private String code;
    private String message;

    ResponseCodes(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
