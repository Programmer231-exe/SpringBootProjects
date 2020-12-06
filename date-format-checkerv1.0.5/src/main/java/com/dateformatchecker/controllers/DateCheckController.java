package com.dateformatchecker.controllers;


import com.dateformatchecker.exceptionhandling.BadRequestException;
import com.dateformatchecker.exceptionhandling.ResourceNotFoundException;
import com.dateformatchecker.model.ResultDate;
import com.dateformatchecker.model.User;
import com.dateformatchecker.services.DateFormatChecker;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@Api(value = "Date Checker Controller")
@RestController
public class DateCheckController {
    private final Logger logger = LogManager.getLogger(DateCheckController.class);
    @Autowired
    private DateFormatChecker dateFormatChecker;

    @RequestMapping("/{id}")
    @ApiOperation(value = "To Handle unmapped Resources",notes = "Handler Mapping Exception handling")
    @ApiResponses(value = {
            @ApiResponse(code =200, message = "Date Checker : Unexpected Request"),
            @ApiResponse(code =404, message = "Date Checker : Resources Not Found")
    })
    @ApiIgnore
    public String wrongRequest(@PathVariable String id){
        logger.info(id + " This is unexpected");
        throw new ResourceNotFoundException("Resource Not Found");
    }
    @ApiOperation(value = "Welcome Page",response =java.lang.String.class ,notes = "Welcome Page of Date Checker")
    @ApiResponses(value = {
            @ApiResponse(code =200, message = "Date Checker : Welcome Page Successfully loaded"),
            @ApiResponse(code =404, message = "Date Checker : Resources Not Found")
    })
    @GetMapping("/")
    public String welcome(){
         String json = "{'message':'Welcome to Date Checker'}";
         return json;
    }



    @PostMapping("/dateCheck")
    @Retryable
    @ApiOperation(value = "Date Checker", response = com.dateformatchecker.model.ResultDate.class,
    notes = "Method responsible for checking the date format and adding the days")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Process successfully completed"),
            @ApiResponse(code = 404,message = "Request Resources not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "user",
                    value = "{\"date\":\"23-01-1999\",\"slo\":\"40\"}",
                    dataType = "com.dateformatchecker.model.User",
                    required = true,
                    paramType = "body"
            )
    })
   public ResultDate checkDate(@RequestBody(required = false) User user){
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
