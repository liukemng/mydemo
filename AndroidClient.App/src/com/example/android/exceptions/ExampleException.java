package com.example.android.exceptions;

public class ExampleException extends Exception {

    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String errorMessage;
    private String requestUri;

    public ExampleException(int errorCode, String errorMessage, String requestUri) {
        this.errorMessage=errorMessage;
        this.errorCode = errorCode;
        this.requestUri = requestUri;
    }

    public int getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public String getRequestUri() {
        return requestUri;
    }

    @Override
    public String toString() {
        return "errorCode:" + this.errorCode + "\nerrorMessage:" + this.errorMessage
                + "\nrequestUri:" + this.requestUri;
    }

}