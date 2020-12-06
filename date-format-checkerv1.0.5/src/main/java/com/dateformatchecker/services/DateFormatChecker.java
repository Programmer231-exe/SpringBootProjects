package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatChecker {
    private final Logger logger = LogManager.getLogger(DateFormatChecker.class);

     private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public boolean checkDateFormat(String userEnteredDateInString) {
        LocalDate userEnteredDate;
        try {
            userEnteredDate = LocalDate.parse(userEnteredDateInString, dateTimeFormatter);
            logger.debug(userEnteredDate);
        } catch (DateTimeParseException de) {
            throw new DateFormatMismatchException(de.getMessage());
        }
        if(userEnteredDate != null)
            return true;
        else
            return false;
    }

    public String addDays(String userDate,String userNumber){
        LocalDate userEnteredDate = null;
        Integer numberOfDaysNeedToBeAdded = null;
        LocalDate updatedDate = null;
        try{
            userEnteredDate = LocalDate.parse(userDate, dateTimeFormatter);
            numberOfDaysNeedToBeAdded = Integer.parseInt(userNumber);
        }catch(DateTimeParseException dtpe){
            logger.warn(dtpe.getMessage());
            throw new DateFormatMismatchException(dtpe.getMessage());
        }catch(NumberFormatException nfe){
            throw new NumberFormatException(nfe.getMessage());
        }

        if(userEnteredDate != null && numberOfDaysNeedToBeAdded != null){
            switch(numberOfDaysNeedToBeAdded){
                case 30:
                    updatedDate = userEnteredDate.plusDays(30);
                    logger.info("30 days added to the user entered date");
                    break;
                case 40:
                    updatedDate = userEnteredDate.plusDays(40);
                    logger.info("40 days added to the user entered date");
                    break;
                case 50:
                    updatedDate = userEnteredDate.plusDays(50);
                    logger.info("50 days added to the user entered date");
                    break;
                default:
                    logger.warn("Not a proper choice");
                    break;
            }
        }

        if(updatedDate != null){
            return updatedDate.format(dateTimeFormatter).toString();
        }else{
            return "Date Cannot be added";
        }

    }
}
