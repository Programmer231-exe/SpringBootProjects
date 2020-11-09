package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.propertiesclass.ConstantDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatChecker {
    private final Logger logger = LogManager.getLogger(DateFormatChecker.class);
    @Autowired
    private ConstantDate constantDateInString;
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean checkDateFormat(String userEnteredDateInString) throws DateFormatMismatchException {
        logger.debug(constantDateInString.toString());
        logger.debug(constantDateInString.getDate());
        LocalDate constantDateInObject = LocalDate.parse(constantDateInString.getDate(), df);
        logger.debug(constantDateInObject);
        logger.debug(constantDateInObject);
        LocalDate userEnteredDate;
        try {
            userEnteredDate = LocalDate.parse(userEnteredDateInString, df);
            logger.debug(userEnteredDate);
        } catch (DateTimeParseException de) {
            throw new DateFormatMismatchException(de.getMessage());
        }
        if (userEnteredDate.isBefore(constantDateInObject) || userEnteredDate.equals(constantDateInObject)) {
            return true;
        }
        if (userEnteredDate.isAfter(constantDateInObject)) ;
        {
            return false;
        }
    }
}
