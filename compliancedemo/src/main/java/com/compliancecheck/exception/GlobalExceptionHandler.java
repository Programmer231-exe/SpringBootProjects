package com.compliancecheck.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(basePackages = {"com.compliancecheck"})
public class GlobalExceptionHandler {
    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidMemberNumberException.class)
    public ResponseEntity<String> dateFormatError(InvalidMemberNumberException imne){
        logger.warn("Date format mismatch exception occurs");
        return new ResponseEntity<>("Please enter a valid member number", HttpStatus.BAD_REQUEST);
    }
}
