package com.dateformatchecker.exceptionhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.dateformatchecker"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(DateFormatMismatchException.class)
    public String dateFormatError(DateFormatMismatchException dfme, Model model){
        logger.warn("Date format mismatch exception occurs");
        model.addAttribute("error",dfme);
        return "dateformat_error";
    }
}
