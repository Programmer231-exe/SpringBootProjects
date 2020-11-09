package com.dateformatchecking.config.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {


    private final Logger logger= LogManager.getLogger(HomeController.class);

    @GetMapping("/anotherindex")
    public String yesorNo(){
        logger.info("This is india ,,,, Laws plays here");
        return "index";
    }
}
