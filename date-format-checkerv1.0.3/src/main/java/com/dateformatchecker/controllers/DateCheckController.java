package com.dateformatchecker.controllers;


import com.dateformatchecker.exceptionhandling.BadRequestException;
import com.dateformatchecker.exceptionhandling.ResourceNotFoundException;
import com.dateformatchecker.model.ResultDate;
import com.dateformatchecker.model.User;
import com.dateformatchecker.services.DateFormatChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DateCheckController {
    private final Logger logger = LogManager.getLogger(DateCheckController.class);
    @Autowired
    private DateFormatChecker dateFormatChecker;

    @RequestMapping("/{id}")
    public String wrongRequest(@PathVariable String id){
        logger.info(id + " This is unexpected");
        throw new ResourceNotFoundException("Resource Not Found");
    }
    @GetMapping("/")
    public String welcome(){
         String json = "{'message':'Welcome to Date Checker'}";
         return json;
    }



    @PostMapping("/dateCheck")
    public ResultDate checkDate(@RequestBody(required = false)User user){
        if(user == null){
            throw new BadRequestException("Please enter the date and slo");
        }
        logger.info(user.getDate());
        logger.info(user.getSlo());
        String updatedDay;

        if(user.getDate()== null && user.getSlo() == null){
            throw new BadRequestException("Date and SLO Should not be empty. Enter a valid values");
        }

        if(dateFormatChecker.checkDateFormat(user.getDate())){
               updatedDay = dateFormatChecker.addDays(user.getDate(),user.getSlo());
                return new ResultDate(updatedDay);
        }else{
             throw new RuntimeException("Days cannot be added to the specified date");
        }

    }


}
