package com.dateformatchecker.model;

public class ErrorMessages {
    private String errorCode;
    private String timestamp;
    private String message;

    public ErrorMessages(String errorCode, String timestamp, String message) {
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessages(){
        super();
    }
}
