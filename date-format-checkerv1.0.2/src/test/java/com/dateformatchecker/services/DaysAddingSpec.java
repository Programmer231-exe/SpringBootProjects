package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DaysAddingSpec {
    @InjectMocks
    private DateFormatChecker dateformatChecker;

    @Test
    public void testAddDaysMethod(){
       Assertions.assertAll(
               () -> assertEquals("2020-11-19",dateformatChecker.addDays("2020-10-20","30")) ,
               () -> assertEquals("2020-11-29",dateformatChecker.addDays("2020-10-20","40")),
               () -> assertEquals("2020-12-09",dateformatChecker.addDays("2020-10-20","50")),
               () -> assertEquals("Date Cannot be added",dateformatChecker.addDays("2020-10-20","270"))
               );
    }

    @Test
    public void testAddDaysMethodWithWrongValues(){
        Assertions.assertAll(
                () -> assertNotEquals("2020-11-09",dateformatChecker.addDays("2020-10-23","30")),
                () -> assertNotEquals("2020-11-31",dateformatChecker.addDays("2020-10-25","40")),
                () -> assertNotEquals("2020-12-25", dateformatChecker.addDays("2020-11-23","50")),
                () -> assertNotEquals("Proper choice",dateformatChecker.addDays("2020-11-25","80"))

        );
    }


    @Test
    public void dateformatMismatchExceptionTest(){
        Exception exception = assertThrows(DateFormatMismatchException.class,() -> dateformatChecker.addDays("2020-31-10","50"));
        if(exception != null)
            assertEquals("DateFormatMismatchException",exception.getClass().getSimpleName());
    }


}
