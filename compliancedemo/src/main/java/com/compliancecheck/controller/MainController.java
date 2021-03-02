package com.compliancecheck.controller;

import com.compliancecheck.exception.InvalidMemberNumberException;
import com.compliancecheck.model.AccountListOfUser;
import com.compliancecheck.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/getAccounts/{membernumber}")
    public ResponseEntity<AccountListOfUser> getUserAccounts(@PathVariable("membernumber") String memberNumber){
        if(memberService.isNumeric(memberNumber)){
            return new ResponseEntity<>(memberService.getUserAccounts(memberNumber), HttpStatus.OK);
        }else{
            throw new InvalidMemberNumberException();
        }
    }
}
