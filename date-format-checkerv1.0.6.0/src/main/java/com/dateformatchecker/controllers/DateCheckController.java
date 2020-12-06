package com.dateformatchecker.controllers;


import com.dateformatchecker.exceptionhandling.ResourceNotFoundException;
import com.dateformatchecker.model.ResultDate;
import com.dateformatchecker.services.AddDays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class DateCheckController {
    private final Logger logger = LogManager.getLogger(DateCheckController.class);
    @Autowired
    private AddDays addDays;

    @RequestMapping("/{id}")
    public String wrongRequest(@PathVariable String id){
        logger.info(id + " This is unexpected");
        throw new ResourceNotFoundException("Resource Not Found");
    }
    @GetMapping("/")
    public String welcome(){
         String welcome = new String("Welcome to Date Checker");
         return welcome;
    }



    @CrossOrigin(origins = {"","http://localhost:8082","http://localhost:3000"})
    @GetMapping("/datereceived")
    public ResultDate processDateChecker(){
        return addDays.getResultDate();

    }


}
