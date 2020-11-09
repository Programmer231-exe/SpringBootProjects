package com.dateformatchecker.controllers;

import com.dateformatchecker.services.DateFormatChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DateCheckController {
    private final Logger logger = LogManager.getLogger(DateCheckController.class);
    @Autowired
    private DateFormatChecker dateFormatChecker;


    @GetMapping("/")
    public String welcome(){
        return "index";
    }
    @GetMapping("/dateCheck")
    public String checkDate(@RequestParam("userDate")String date,@RequestParam("userNumber")String userNumber,Model model){
        String updatedDay ;
        if(dateFormatChecker.checkDateFormat(date)){
                model.addAttribute("message","True : Entered Date "+ date + " is Before or equals to constant date");
                updatedDay = dateFormatChecker.addDays(date,userNumber);
                model.addAttribute("updatedDate",updatedDay);
                logger.info("Updated Day is " + updatedDay);
                logger.info("User Entered Date is Before or equals to constant date");
        }else{
                model.addAttribute("message","False : entered date "+ date +" is after the constant date.");
                logger.info("User entered date is after the constant date.");
        }
        return "index";
    }


}
