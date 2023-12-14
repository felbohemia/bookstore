package com.atamie.mark.bookstore.utils;

public enum Messages {
    _NOTFOUND("NO AUTHOR WITH GIVEN ID OUR SYSTEM YET"),
    _BOOKISBNNOTFOUND("BOOK ISBN NOT FOUND"),
    _AUTHOREXIST("AUTHOR'S EMAIL ALREADY EXIST");


    private String message;

    Messages(String val)
    {
        this.message = val;
    }

    public String getMessage()
    {
        return message;
    }
}
