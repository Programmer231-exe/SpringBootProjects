package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DaysAddingSpec {
    @Autowired
    private DateFormatChecker dateformatChecker;

    @Test
    public void checkDateFormatTester(){
        assertTrue(dateformatChecker.checkDateFormat(new User("23-01-1999","30")));
    }


}
