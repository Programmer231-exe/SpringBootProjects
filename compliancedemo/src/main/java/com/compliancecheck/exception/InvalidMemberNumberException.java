package com.compliancecheck.exception;

public class InvalidMemberNumberException extends RuntimeException {
    public InvalidMemberNumberException(String message){
        super(message);
    }
    public InvalidMemberNumberException(){
        super();
    }
}
