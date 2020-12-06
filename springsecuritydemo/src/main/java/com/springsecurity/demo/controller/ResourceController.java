package com.springsecurity.demo.controller;

import com.springsecurity.demo.model.MyProtectedResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@RestController
@RequestMapping(value = "/protectedresources")
public class ResourceController {

    private final Logger logger = LogManager.getLogger();

    @GetMapping(value = "/{id}")
    public MyProtectedResources findOne(@PathVariable Long id){
        return new MyProtectedResources(Long.parseLong(randomNumeric(2)),randomAlphabetic(4));
    }

    @GetMapping
    public List<MyProtectedResources> findAll(){
        List<MyProtectedResources> resourcesList = new ArrayList<>();
        resourcesList.add(new MyProtectedResources(Long.parseLong(randomNumeric(2)),randomAlphabetic(4)));
        resourcesList.add(new MyProtectedResources(Long.parseLong(randomNumeric(2)),randomAlphabetic(4)));
        resourcesList.add(new MyProtectedResources(Long.parseLong(randomNumeric(2)),randomAlphabetic(4)));
        resourcesList.add(new MyProtectedResources(Long.parseLong(randomNumeric(2)),randomAlphabetic(4)));
        return resourcesList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody MyProtectedResources resources){
        logger.info("Resources created");
    }
}
