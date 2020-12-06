package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.kafkaproducer.SendToKafkaCluster;
import com.dateformatchecker.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatChecker {
    @Autowired
    private SendToKafkaCluster dataSender;

    private final Logger LOGGER = LogManager.getLogger(DateFormatChecker.class);

     private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


     public boolean checkDateFormat(User user){
         LocalDate userEnteredDate;
         try{
             userEnteredDate = LocalDate.parse(user.getDate(),dateTimeFormatter);
             LOGGER.info("User Entered Date parse Successfully");
             LOGGER.info(dataSender);
             if(dataSender.send(user)){
                 LOGGER.info("Date successfully uploaded to cluster");
                 return true;
             }else{
                 return false;
             }
         }catch(DateTimeParseException de){
             throw new DateFormatMismatchException(de.getMessage());
         }
     }



}
