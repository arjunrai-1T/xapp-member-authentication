package com.xapp.member.authentication.exceptions;

public class ErrorResponseException  extends RuntimeException{
    private String message;
    private int statusCode;

    public ErrorResponseException(String message) {
        super(message);
        this.message = message;
        this.statusCode = 500;  // HTTP 500 Internal Server Error
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
