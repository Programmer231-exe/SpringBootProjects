package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.kafkaconsumer.KafkaConsumerDateFetcher;
import com.dateformatchecker.model.ResultDate;
import com.dateformatchecker.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class AddDays {
    private final Logger logger = LogManager.getLogger(AddDays.class);
    @Autowired
    private KafkaConsumerDateFetcher kafkaConsumerDateFetcher;

     private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ResultDate addDays(User user){
        LocalDate userEnteredDate = null;
        Integer numberOfDaysNeedToBeAdded = null;
        LocalDate updatedDate = null;
        try{
            userEnteredDate = LocalDate.parse(user.getDate(), dateTimeFormatter);
            numberOfDaysNeedToBeAdded = Integer.parseInt(user.getSlo());
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
            return new ResultDate(updatedDate.format(dateTimeFormatter).toString());
        }else{
            return null;
        }

    }

    public ResultDate getResultDate() {
        return addDays(kafkaConsumerDateFetcher.getUser());
    }
}
