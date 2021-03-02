package com.compliancecheck.service;

import com.compliancecheck.services.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Test
    void isNumericCheckedCorrectly(){
        assertTrue("Positive Tests",memberService.isNumeric("298374928374982734"));
    }
}
