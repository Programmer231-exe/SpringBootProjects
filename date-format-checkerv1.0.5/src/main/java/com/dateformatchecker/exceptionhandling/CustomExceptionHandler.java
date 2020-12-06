package com.dateformatchecker.exceptionhandling;

import com.dateformatchecker.model.ErrorMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = {"com.dateformatchecker"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(DateFormatMismatchException.class)
    public ErrorMessages dateFormatError(DateFormatMismatchException dfme){
        logger.warn("Date format mismatch exception occurs");
        return new ErrorMessages("400",LocalDateTime.now().toString(),dfme.getLocalizedMessage() + "\n" +
                "Note : Expected Date format is dd-MM-yyyy");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ErrorMessages numberFormatException(NumberFormatException nfe){
        logger.warn("Number of Format exception occurs");
        return new ErrorMessages("400",LocalDateTime.now().toString(),nfe.getLocalizedMessage() + "\n"+
                "Note : Expected values are 30,40,50");
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorMessages handleError(RuntimeException runtimeException) {
       return new ErrorMessages("500",LocalDateTime.now().toString(),runtimeException.getLocalizedMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessages resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return new ErrorMessages("404",LocalDateTime.now().toString(),resourceNotFoundException.getLocalizedMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorMessages badRequestException(BadRequestException badRequestException){
        return new ErrorMessages("400",LocalDateTime.now().toString(),badRequestException.getLocalizedMessage());
    }

}
