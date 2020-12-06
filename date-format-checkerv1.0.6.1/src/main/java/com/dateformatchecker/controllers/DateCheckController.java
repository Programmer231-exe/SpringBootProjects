package com.dateformatchecker.controllers;


import com.dateformatchecker.exceptionhandling.BadRequestException;
import com.dateformatchecker.exceptionhandling.ResourceNotFoundException;
import com.dateformatchecker.model.User;
import com.dateformatchecker.services.DateFormatChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@CrossOrigin("http://localhost:3000")
public class DateCheckController {
    @Autowired
    private DateFormatChecker dateFormatChecker;

    private final Logger logger = LogManager.getLogger(DateCheckController.class);


    @RequestMapping("/{id}")
    public String wrongRequest(@PathVariable String id) {
        logger.info(id + " This is unexpected");
        throw new ResourceNotFoundException("Resource Not Found");
    }

    @GetMapping("/")
    public String welcome() {
        String welcome = new String("Welcome to Date Checker");
        return welcome;
    }


    @PostMapping("/dateCheck")
    public void checkDate(@RequestBody(required = false) User user, HttpServletResponse response) {
        if (user == null) {
            throw new BadRequestException("Please enter the date and slo");
        }
        logger.info(user.getDate());
        logger.info(user.getSlo());


        if (user.getDate() == null && user.getSlo() == null) {
            throw new BadRequestException("Date and SLO Should not be empty. Enter a valid values");
        }

        if(dateFormatChecker.checkDateFormat(user)) {
            try{
                logger.info("Request redirected to another server");

                response.sendRedirect("http://localhost:8083/datereceived");
            }catch(IOException ioe){
                throw new BadRequestException("Bad Request");
            }
        } else {
            throw new BadRequestException("Days cannot be added to the specified date");
        }

    }

}